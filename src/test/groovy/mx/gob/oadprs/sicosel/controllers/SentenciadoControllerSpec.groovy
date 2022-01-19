package mx.gob.oadprs.sicosel.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import mx.gob.oadprs.sicosel.dto.SentenciadoDto
import mx.gob.oadprs.sicosel.enums.Sexo
import mx.gob.oadprs.sicosel.services.SentenciadoService
import mx.gob.oadprs.sicosel.validator.FamiliarValidador
import mx.gob.oadprs.sicosel.validator.SentenciadoValidador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SentenciadoControllerSpec extends Specification {

    public static final int MEXICO_ID = 82
    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

    @Autowired
    SentenciadoService sentenciadoService

    def "Deberia crear un sentenciado"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        Map cmd = [:]
        cmd.with {
            nombre = 'Alejandro'
            apellidoPaterno = 'Ràmirez'
            apellidoMaterno = 'Torres'
            nacionalidadId = MEXICO_ID
            estadoId = 13
            documento = 'HELA880416HHGRZL08'
            estadoCivil = 1
            alias = "el pinky"
            otrosNombres =  "Enrique Peña"
            fechaNacimiento = "1988-04-16"
            ocupacionId = 1
            sexo = Sexo.MASCULINO
            etniaId = 1
            escolaridad = 1
            telefonoFijo =  1234567890
            celular =  1234567890
            correoElectronico = 'juan.antonio.perez.garcia@gmail.com'
        }

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
           assert it.nombre == 'Alejandro'
            assert it.folio == 'RXTA88041613HMEX001'
            assert it.apellidoPaterno == 'Ràmirez'
            assert it.apellidoMaterno == 'Torres'
            assert it.nacionalidad == 'MÉXICO'
            assert it.documento == 'HELA880416HHGRZL08'
            assert it.estadoCivil == 'SOLTERO(A)'
            assert it.alias == "el pinky"
            assert it.otrosNombres ==  "Enrique Peña"
            assert it.fechaNacimiento == '1988-04-16'
            assert it.ocupacion == "EMPLEADO"
            assert it.sexo == 'MASCULINO'
            assert it.etnia == 'AMUZGO'
            assert it.escolaridad == 'SIN ESCOLARIDAD'
            assert it.telefonoFijo ==  '1234567890'
            assert it.celular ==  "1234567890"
            assert it.correoElectronico == 'juan.antonio.perez.garcia@gmail.com'
            assert it.id
        }

    }

    def "Deberia crear un sentenciado caso de QA"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        Map cmd = [:]
        cmd.with {
            apellidoMaterno = "DDD"
            apellidoPaterno =  "DDD"
            celular =  "9999999999"
            correoElectronico =  "anita19@ggd"
            documento = "AINA941015MDFVYN05"
            escolaridad = "5"
            estadoCivil = "5"
            estadoId = "9"
            etniaId = "3"
            fechaNacimiento = "2022-01-07"
            nacionalidadId =  "82"
            nombre =  "DDDD"
            ocupacionId =  "1"
            sexo = "MASCULINO"
        }

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert it.id
            assert apellidoMaterno == "DDD"
            assert apellidoPaterno ==  "DDD"
            assert celular ==  "9999999999"
            assert correoElectronico ==  "anita19@ggd"
            assert documento == "AINA941015MDFVYN05"
            assert escolaridad == "SECUNDARIA COMPLETA"
            assert estadoCivil == "UNIÓN LIBRE"
            assert estado == "CIUDAD DE MÉXICO"
            assert etnia == "COCHIMI"
            assert fechaNacimiento == "2022-01-07"
            assert nacionalidad ==  "MÉXICO"
            assert nombre ==  "DDDD"
            assert ocupacion ==  "EMPLEADO"
            assert sexo == "MASCULINO"
        }

    }

    def "Deberia crear un sentenciado sin campos opcionales"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        Map cmd = [:]
        cmd.with {
            celular =  "9999999999"
            correoElectronico =  "anita19@ggd"
            documento = "AINA941015MDFVYN05"
            estadoCivil = "5"
            estadoId = "9"
            fechaNacimiento = "2022-01-07"
            nacionalidadId =  "82"
            nombre =  "DDDD"
            sexo = "MASCULINO"
        }

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert it.id
            assert it.folio
            assert celular == "9999999999"
            assert correoElectronico == "anita19@ggd"
            assert documento == "AINA941015MDFVYN05"
            assert estadoCivil == "UNIÓN LIBRE"
            assert estado == "CIUDAD DE MÉXICO"
            assert fechaNacimiento == "2022-01-07"
            assert nacionalidad ==  "MÉXICO"
            assert nombre ==  "DDDD"
            assert sexo == "MASCULINO"
        }

    }

    def "Should not post on invalid args "(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        SentenciadoValidador cmd = new SentenciadoValidador()
        cmd.with {
            apellidoPaterno = 'Pèrez'
            apellidoMaterno = 'Garcìa'
            nacionalidadId = MEXICO_ID
            estadoId = 13
            documento = 'HELA880416HHGRZL08'
            estadoCivil = 1
            alias = "el pinky"
            otrosNombres =  "Enrique Peña"
            fechaNacimiento = LocalDate.of(1988, Month.APRIL, 16)
            ocupacionId = 1
            sexo = Sexo.FEMENINO
            etniaId = 1
            escolaridad =  1
            telefonoFijo =  1234567890
            celular =  1234567890
            correoElectronico = 'juan.antonio.perez.garcia@gmail.com'
        }

        when:
        rest.postForObject("http://localhost:${ port }/sentenciado", new HttpEntity(cmd, headers), Map)

        then:
        thrown HttpClientErrorException.BadRequest
    }

    def "Deberia crear un familiar del sentenciado"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        FamiliarValidador cmd = new FamiliarValidador()
        cmd.with {
            nombre = 'Chapo Mama'
            apellidoMaterno = 'Loera'
            apellidoPaterno = 'Virginia'
            documento = 'HELA880416HHGRZL08'
            telefonoFijo = "1234567"
            celular = "123123"
            parentescoId = 2
            nacionalidadId =  82
        }

        println(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(cmd))

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado/$sentenciado.id/familiar", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert id
            assert sentenciadoId == sentenciado.id
            assert nombre == 'Chapo Mama'
            assert apellidoPaterno == 'Virginia'
            assert apellidoMaterno == 'Loera'
            assert documento == 'HELA880416HHGRZL08'
            assert telefonoFijo == '1234567'
            assert celular == '123123'
            assert parentesco == 'MADRE'
            assert nacionalidad ==  "MÉXICO"

        }

    }

    def "No deberia crear el sentenciado cuando faltan campos requeridos y responder Bad Request"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        FamiliarValidador cmd = new FamiliarValidador()
        cmd.with {
            apellidoMaterno = 'Loera'
            apellidoPaterno = 'Virginia'
            documento = 'HELA880416HHGRZL08'
            telefonoFijo = "1234567"
            celular = "123123"
        }

        println(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(cmd))

        when:
        rest.postForObject("http://localhost:${ port }/sentenciado/$sentenciado.id/familiar", new HttpEntity(cmd, headers), Map)

        then:
        thrown HttpClientErrorException.BadRequest

    }

    def "Deberia  crear un familiar del sentenciado solo con campos requeridos"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        FamiliarValidador cmd = new FamiliarValidador()
        cmd.with {
            nombre = 'Chapo Mama'
            documento = 'HELA880416HHGRZL08'
            parentescoId = 2
            nacionalidadId =  MEXICO_ID
        }

        println(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(cmd))

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado/$sentenciado.id/familiar", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert id
            assert sentenciadoId == sentenciado.id
            assert nombre == 'Chapo Mama'
            assert apellidoPaterno == null
            assert apellidoMaterno == null
            assert documento == 'HELA880416HHGRZL08'
            assert telefonoFijo == null
            assert celular ==  null
            assert parentesco == 'MADRE'
            assert nacionalidad ==  "MÉXICO"

        }

    }

    private SentenciadoDto sentenciadoGuardado() {
        SentenciadoValidador cmd = new SentenciadoValidador()
        cmd.with {
            nombre = 'Tomas'
            apellidoPaterno = 'Ràmirez'
            apellidoMaterno = 'Torres'
            nacionalidadId = 82
            estadoId = 13
            documento = 'HELA880416HHGRZL08'
            estadoCivil = 1
            alias = "el pinky"
            otrosNombres = "Enrique Peña"
            fechaNacimiento = LocalDate.of(1988, Month.APRIL, 16)
            ocupacionId = 1
            sexo = Sexo.MASCULINO
            etniaId = 1
            escolaridad = 1
            telefonoFijo = 1234567890
            celular = 1234567890
            correoElectronico = 'juan.antonio.perez.garcia@gmail.com'
        }
        return sentenciadoService.crear(cmd)
    }


}

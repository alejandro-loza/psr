package sspc.gob.mx.psr.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import sspc.gob.mx.psr.dto.SentenciadoDto
import sspc.gob.mx.psr.enums.Sexo
import sspc.gob.mx.psr.exeptions.ItemNotFoundException
import sspc.gob.mx.psr.services.SentenciadoService
import sspc.gob.mx.psr.validator.FamiliarValidador
import sspc.gob.mx.psr.validator.SentenciadoValidador

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SentenciadoControllerSpec extends Specification {

    public static final int MEXICO_ID = 82
    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

    @Autowired
    SentenciadoService sentenciadoService;

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
            telefonoFijo = 1234567
            celular = 123123
            parentescoId = 2
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

        }

    }

    def "Debería traer un sentenciado por folio"(){
        given: 'dado un sentenciado guardado'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        when:
        def resp = rest.getForEntity(
                "http://localhost:${ port }/sentenciado/${sentenciado.getFolio()}" , Map)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        resp.getBody().with {
            assert folio == sentenciado.getFolio()
            assert documento == 'HELA880416HHGRZL08'

        }
    }

    def "No debería traer un sentenciado por folio con folio erroneo"(){
        given: 'dado un sentenciado guardado'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        when:
        rest.getForEntity(
                "http://localhost:${ port }/sentenciado/XXXXXXXXXXXX" , Map)

        then:
        thrown ItemNotFoundException

    }

    def "Debería traer un sentenciado por nombre completo"(){
        given: 'dado un sentenciado guardado'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?nombre=${sentenciado.getNombre()}" +
                        "&apellidoPaterno=${sentenciado.getApellidoPaterno()}" +
                        "&apellidoMaterno=${sentenciado.getApellidoMaterno()}", Map)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp.getBody()
    }

    def "Debería traer un sentenciado por nombre y apellido"(){
        given: 'dado un sentenciado guardado'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?nombre=${sentenciado.getNombre()}" +
                        "&apellidoPaterno=${sentenciado.getApellidoPaterno()}", Map)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp.getBody()
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

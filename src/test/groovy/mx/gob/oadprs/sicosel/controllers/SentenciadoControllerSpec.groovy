package mx.gob.oadprs.sicosel.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import mx.gob.oadprs.sicosel.dto.SentenciadoDto
import mx.gob.oadprs.sicosel.services.SentenciadoService
import mx.gob.oadprs.sicosel.validator.FamiliarValidador
import mx.gob.oadprs.sicosel.validator.LoginRequest
import mx.gob.oadprs.sicosel.validator.SentenciadoValidador
import mx.gob.oadprs.sicosel.validator.UserRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.*
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import mx.gob.oadprs.sicosel.enums.Sexo
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
        given:'a body login request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        LoginRequest loginRequest = new LoginRequest()
        loginRequest.with {
            user = "pinky"
            password = "pwd"
        }

        def httpEntity = new HttpEntity<Object>(loginRequest, headers)

        when:
        def response = rest.exchange("http://localhost:${ port }/login",
                HttpMethod.POST, httpEntity, UserRequest)


        then:
        assert response.statusCode == HttpStatus.OK
        assert response.body
        response.body.with {
            assert token
        }


        headers.set("Authorization", "Bearer "+response.body.token)

        and:
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
        given:'a body login request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        LoginRequest loginRequest = new LoginRequest()
        loginRequest.with {
            user = "pinky"
            password = "pwd"
        }

        def httpEntity = new HttpEntity<Object>(loginRequest, headers)

        when:
        def response = rest.exchange("http://localhost:${ port }/login",
                HttpMethod.POST, httpEntity, UserRequest)


        then:
        assert response.statusCode == HttpStatus.OK
        assert response.body
        response.body.with {
            assert token
        }


        headers.set("Authorization", "Bearer "+response.body.token)

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
        given:'a body login request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        LoginRequest loginRequest = new LoginRequest()
        loginRequest.with {
            user = "pinky"
            password = "pwd"
        }

        def httpEntity = new HttpEntity<Object>(loginRequest, headers)

        when:
        def response = rest.exchange("http://localhost:${ port }/login",
                HttpMethod.POST, httpEntity, UserRequest)


        then:
        assert response.statusCode == HttpStatus.OK
        assert response.body
        response.body.with {
            assert token
        }


        headers.set("Authorization", "Bearer "+response.body.token)

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
            nacionalidadId = MEXICO_ID

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

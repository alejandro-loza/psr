package sspc.gob.mx.psr.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import sspc.gob.mx.psr.enums.Sexo
import sspc.gob.mx.psr.validator.SentenciadoValidador

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SentenciadoControllerSpec extends Specification {

    public static final int MEXICO_ID = 484
    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

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
            sexo = Sexo.FEMENINO
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
            assert it.apellidoPaterno == 'Ràmirez'
            assert it.apellidoMaterno == 'Torres'
            assert it.nacionalidad == 'México'
            assert it.documento == 'HELA880416HHGRZL08'
            assert it.estadoCivil == 'SOLTERO(A)'
            assert it.alias == "el pinky"
            assert it.otrosNombres ==  "Enrique Peña"
            assert it.fechaNacimiento == '1988-04-16'
            assert it.ocupacion == "EMPLEADO"
            assert it.sexo == 'FEMENINO'
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

}

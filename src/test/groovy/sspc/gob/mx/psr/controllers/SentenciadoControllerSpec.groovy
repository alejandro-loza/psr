package sspc.gob.mx.psr.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import sspc.gob.mx.psr.validator.SentenciadoValidador

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SentenciadoControllerSpec extends Specification {

    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

    def "Deberia crear un sentenciado"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        SentenciadoValidador cmd = new SentenciadoValidador()
        cmd.with {
            nombre = 'Juan Antonio'
            apellidoPaterno = 'Pèrez'
            apellidoMaterno = 'Garcìa'
            nacionalidad = 'Mexìcana'
            curp = 'HELA880416HHGRZL08'
            estadoCivil ='soltero'
            alias = "el pinky"
            otrosNombres =  "Enrique Peña"
            fechaNacimiento = LocalDate.of(1988, Month.APRIL, 16)
            ocupacion = "Servidor publico"
            sexo = 'Femenino'
            etnia = 'Maya'
            escolaridad = 'Primaria'
            telefonoFijo =  1234567890
            celular =  1234567890
            email = 'juan.antonio.perez.garcia@gmail.com'
        }

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
           assert it.nombre == 'Juan Antonio'
            assert it.apellidoPaterno == 'Pèrez'
            assert it.apellidoMaterno == 'Garcìa'
            assert it.nacionalidad == 'Mexìcana'
            assert it.curp == 'HELA880416HHGRZL08'
            assert it.estadoCivil == 'soltero'
            assert it.alias == "el pinky"
            assert it.otrosNombres ==  "Enrique Peña"
            assert it.fechaNacimiento == '1988-04-16'
            assert it.ocupacion == "Servidor publico"
            assert it.sexo == 'Femenino'
            assert it.etnia == 'Maya'
            assert it.escolaridad == 'Primaria'
            assert it.telefonoFijo ==  '1234567890'
            assert it.celular ==  "1234567890"
            assert it.email == 'juan.antonio.perez.garcia@gmail.com'
            assert it.id
            assert it.dateCreated
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
            nacionalidad = 'Mexìcana'
            curp = 'HELA880416HHGRZL08'
            estadoCivil ='soltero'
            alias = "el pinky"
            otrosNombres =  "Enrique Peña"
            fechaNacimiento = LocalDate.of(1988, Month.APRIL, 16)
            ocupacion = "Servidor publico"
            sexo = 'Femenino'
            etnia = 'Maya'
            escolaridad = 'Primaria'
            telefonoFijo =  1234567890
            celular =  1234567890
            email = 'juan.antonio.perez.garcia@gmail.com'
        }

        when:
        rest.postForObject("http://localhost:${ port }/sentenciado", new HttpEntity(cmd, headers), Map)

        then:
        thrown HttpClientErrorException.BadRequest
    }

}

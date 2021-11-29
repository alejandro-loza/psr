package sspc.gob.mx.psr.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import sspc.gob.mx.psr.validator.SentencedValidator

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SentencedControllerSpec extends Specification {

    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()


    def "Should post and create a sentenced"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        SentencedValidator cmd = new SentencedValidator()
        cmd.with {
            name = 'Juan Antonio'
            firstName = 'Pèrez'
            lastName = 'Garcìa'
            nationality = 'Mexìcana'
            curp = 'HELA880416HHGRZL08'
            maritalStatus ='soltero'
            alias = "el pinky"
            otherNames =  "Enrique Peña"
            birthDate = 1587567125458
            occupation = "Servidor publico"
            gender = 'Femenino'
            ethnicity = 'Maya'
            schooling = 'Primaria'
            homePhone =  1234567890
            mobilePhone =  1234567890
            email = 'juan.antonio.perez.garcia@gmail.com'
        }

        println(cmd.toString())

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenced", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
           assert it.name == 'Juan Antonio'
            assert it.firstLastName == 'Pèrez'
            assert it.secondLastName == 'Garcìa'
            assert it.nationality == 'Mexìcana'
            assert it.curp == 'HELA880416HHGRZL08'
            assert it.maritalStatus == 'soltero'
            assert it.alias == "el pinky"
            assert it.otherNames ==  "Enrique Peña"
            assert it.birthDate == 1587567125458
            assert it.occupation == "Servidor publico"
            assert it.gender == 'Femenino'
            assert it.ethnicity == 'Maya'
            assert it.schooling == 'Primaria'
            assert it.homePhone ==  1234567890
            assert it.mobilePhone ==  1234567890
            assert it.email == 'juan.antonio.perez.garcia@gmail.com'
            assert it.id
            assert it.dateCreated
        }
      //  assert  org.apache.commons.lang3.StringUtils.stripAccents("Añ")

    }

    def "Should not post on invalid args "(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        SentencedValidator cmd = new SentencedValidator()
        cmd.with {
            firstName = 'Pèrez'
            lastName = 'Garcìa'
            nationality = 'Mexìcana'
            curp = 'HELA880416HHGRZL08'
            maritalStatus ='soltero'
            alias = "el pinky"
            otherNames =  "Enrique Peña"
            birthDate = 1587567125458
            occupation = "Servidor publico"
            gender = 'Femenino'
            ethnicity = 'Maya'
            schooling = 'Primaria'
            homePhone =  1234567890
            mobilePhone =  1234567890
            email = 'juan.antonio.perez.garcia@gmail.com'
        }

        when:
        rest.postForObject("http://localhost:${ port }/sentenced", new HttpEntity(cmd, headers), Map)

        then:
        thrown HttpClientErrorException.BadRequest
    }

}

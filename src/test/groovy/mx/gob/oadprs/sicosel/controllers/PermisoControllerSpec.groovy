package mx.gob.oadprs.sicosel.controllers

import mx.gob.oadprs.sicosel.dto.LoginDto
import mx.gob.oadprs.sicosel.validator.LoginRequestValidador
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PermisoControllerSpec extends Specification {
    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()


    def "Should get permiso"() {
        given: 'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        LoginDto loginDto = new LoginDto()
        loginDto.with {
            rol = "ROL_ADMON"
        }

        def httpEntity = new HttpEntity<Object>(loginDto, headers)

        when:
        def response = rest.postForObject("http://localhost:${port}/permiso", new HttpEntity(loginDto, headers), Map)

        then:
        assert response.statusCode == HttpStatus.OK
        assert response.body

    }

    def "Deberia hacer login a prs"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        LoginRequestValidador loginRequest = new LoginRequestValidador()
        loginRequest.with {
            usuario = "accesod.infotec@oadprs.gob.mx"
            contrasenia = "MXFhejJ3c1g="
        }

        def httpEntity = new HttpEntity<Object>(loginRequest, headers)

        when:
        def response = rest.postForObject("http://localhost:${ port }/prsLogin", new HttpEntity(loginRequest, headers), Map)

        then:
        response.with {
            assert token
        }
    }

}

package mx.gob.oadprs.sicosel.controllers

import mx.gob.oadprs.sicosel.validator.LoginRequestValidador
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import spock.lang.Ignore
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerSpec extends Specification {
    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()


    def "Should get login"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        LoginRequestValidador loginRequest = new LoginRequestValidador()
        loginRequest.with {
            usuario = "pinky"
            contrasenia = "pwd"
        }

        when:
        def response = rest.postForObject("http://localhost:${ port }/login", new HttpEntity(loginRequest, headers), Map)

        then:
        response.with {
            assert token
        }


    }

    @Ignore
    def "Deberia hacer login a prs"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        LoginRequestValidador loginRequest = new LoginRequestValidador()
        loginRequest.with {
            usuario = "accesod.infotec@oadprs.gob.mx"
            contrasenia = "MXFhejJ3c1g="
        }

        when:
        def response = rest.postForObject("http://localhost:${ port }/login/prs",
                new HttpEntity<Object>(loginRequest, headers), Map)

        then:
        response.with {
            assert usuario == "CN=ACCESO INFOTEC,OU=GENERICAS,OU=CTICS,OU=DGA,OU=PYRS,DC=oadprs,DC=gob,DC=mx"
            assert sistema == "SICOSEL"
            assert roles == ["ADMIN", "CONSULTA", "AUDITORIA"]
            assert token != null

        }
    }

}

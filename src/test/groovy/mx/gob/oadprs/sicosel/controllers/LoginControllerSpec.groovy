package mx.gob.oadprs.sicosel.controllers

import mx.gob.oadprs.sicosel.validator.LoginRequest
import mx.gob.oadprs.sicosel.validator.UserRequest
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

    def "Deberia traer los datos del usuarios autentificado"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        LoginRequest loginPrincipalValidador= new LoginRequest()
        loginPrincipalValidador.with {
            usuario = "sicosel.presentavig@oadprs.gob.mx"
            contrasenia = "mmUzFSJyqsFxUd/QIR6UjiP1Bbn4mXQcjtHXOnmvbtz/8IAaApG5dsPLuA78oHmYu15ZAGzcQgypwJoAS25qpQ=="
        }

        when:
        def resp = rest.postForObject("http://localhost:${ port }/login",
                new HttpEntity(loginPrincipalValidador, headers), UserRequest)

        then:
        assert resp.getToken() != null
    }

}

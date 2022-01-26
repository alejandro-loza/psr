package mx.gob.oadprs.sicosel.controllers

import mx.gob.oadprs.sicosel.validator.LoginRequestValidador
import mx.gob.oadprs.sicosel.validator.UserRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaisControllerSpec extends Specification {

    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

    def "Deberia traer todos los paises "(){
        given:'a body login request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        LoginRequestValidador loginRequest = new LoginRequestValidador()
        loginRequest.with {
            usuario = "pinky"
            contrasenia = "pwd"
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

        when:
        def resp = rest.exchange("http://localhost:${ port }/catalogo/pais",
                HttpMethod.GET, httpEntity, List)?.body

        then:
        assert resp.size() == 130
        def mex = resp.find {it.nombre == 'MÉXICO'}

        mex.with {
            assert it.nombre == 'MÉXICO'
            assert it.alfa3 == 'MEX'
        }
    }


}

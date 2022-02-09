package mx.gob.oadprs.sicosel.controllers

import mx.gob.oadprs.sicosel.validator.PermisoRequestValidador
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
class PermisoControllerSpec extends Specification {
    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()


    def "Should get permission"() {
        given: 'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        when:
        def response = rest.getForEntity("http://localhost:${ port }/login/permisos/ROL_ADMON", List)

        then:
        assert response.statusCode == HttpStatus.OK
        response.with {
            assert rol == "ROL_ADMON"
            assert alcance == "RACI"
            assert modulo == "GENERAL"
            assert path == "sentenciado"
        }
    }
}

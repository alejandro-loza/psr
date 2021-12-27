package sspc.gob.mx.psr.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaisControllerSpec extends Specification {

    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

    def "Deberia traer todos los paises "(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogos/pais", List)?.body

        then:
        def mex = resp.find {it.id == 484}

        mex.with {
            assert it.nombre == 'México'
            assert it.alpha3 == 'MEX'
        }
    }


}

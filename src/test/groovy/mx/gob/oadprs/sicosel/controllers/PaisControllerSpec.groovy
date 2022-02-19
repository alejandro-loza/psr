package mx.gob.oadprs.sicosel.controllers

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
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/pais", List)?.body

        then:
       // assert resp.size() == 130
        assert resp.size() == 131
        def mex = resp.find {it.nombre == 'MÉXICO'}

        mex.with {
            assert it.nombre == 'MÉXICO'
            assert it.alfa3 == 'MEX'
        }
    }


}

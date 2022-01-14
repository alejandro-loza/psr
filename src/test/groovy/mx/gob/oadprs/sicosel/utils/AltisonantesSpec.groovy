package mx.gob.oadprs.sicosel.utils

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AltisonantesSpec extends Specification{

    def "Deberia obtener las palabras altisonantes como singleton"(){
        when:
        def resp = Altisonantes.listado()

        then:
        assert resp.size() == 81
        resp.with {
            assert resp.BACA == 'BXCA'
            assert resp.WUEY == 'WXEY'
        }

        when:
        def res = Altisonantes.listado()

        then:
        assert res


    }

}

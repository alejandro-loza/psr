package mx.gob.oadprs.sicosel.utils

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UtileriasSpec extends Specification {

    def "Deberia recuperar contraseña desencriptada"() {

        when:
        def res = SeguridadLogin.desencriptarRSA  ("eVNoJVDt4v+4UiTLJzgSfjFjhtcgFdU/Q8J/5hOvVC/fcXqkACrXBSmRirWrvtijvacpLCnNKPpgTezTRxFaUA==")

        then:
        assert res != null
    }

}

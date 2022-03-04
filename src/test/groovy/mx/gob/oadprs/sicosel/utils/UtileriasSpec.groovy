package mx.gob.oadprs.sicosel.utils

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UtileriasSpec extends Specification {

    def "Deberia recuperar contraseña desencriptada"() {

        when:
        def res = SeguridadLogin.desencriptarRSA  ("mmUzFSJyqsFxUd/QIR6UjiP1Bbn4mXQcjtHXOnmvbtz/8IAaApG5dsPLuA78oHmYu15ZAGzcQgypwJoAS25qpQ==")

        then:
        assert res == 'Mexic@22'
    }

}

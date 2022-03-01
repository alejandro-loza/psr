package mx.gob.oadprs.sicosel.utils

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UtileriasSpec extends Specification {

    def "Deberia recuperar contraseña encriptada"() {
        when:
        def resp = SeguridadLogin.encriptarAES("Mexic@22")

        then:
        assert resp != null

        when:
        def res = SeguridadLogin.desencriptarAES(resp)

        then:
        assert res != null
    }

    def "Deberia recuperar contraseña encriptada a 64"() {
        when:
        def resp = SeguridadLogin.codificar64("Mexic@22")

        then:
        assert resp != null

        when:
        def res = SeguridadLogin.decodificar64(resp)

        then:
        assert res != null
    }
}

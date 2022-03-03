package mx.gob.oadprs.sicosel.utils

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UtileriasSpec extends Specification {

    def "Deberia recuperar contraseña encriptada"() {
    /*    when:
        def resp = SeguridadLogin.encriptarAES("1qaz2wsX")

        then:
        assert resp != null
*/
        when:
        def res = SeguridadLogin.desencriptarAES('TURFeU16UTFOamM0T1VGQ1EwUkZSa1RpdmsxVTllWU4NCg==')

        then:
        assert res != null
    }

    def "Deberia recuperar contraseña encryptMessage"() {
          when:
            def resp = SeguridadLogin.encryptMessage("1qaz2wsX")

            then:
            assert resp != null

        when:
        def res = SeguridadLogin.decryptMessage(resp)

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

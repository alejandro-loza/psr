package sspc.gob.mx.psr.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import sspc.gob.mx.psr.validator.SentenciadoValidador

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FolioServiceSpec extends Specification{

    @Autowired
    FolioService folioService

    def "Debe crear folio"(){
        given:
        SentenciadoValidador cmd = new SentenciadoValidador()
        cmd.with {
            nombre = 'Juan Antonio'
            apellidoPaterno = 'Ñéral'
            apellidoMaterno = 'Garcìa'
            nacionalidad = 'Mexìcana'
            fechaNacimiento = LocalDate.of(1988, Month.APRIL, 16)
            sexo = 'Femenino'
        }

        when:
        def resp = folioService.generar(cmd)

        then:
        assert resp
    }
}

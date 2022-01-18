package sspc.gob.mx.psr.services

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import sspc.gob.mx.psr.enums.Sexo
import sspc.gob.mx.psr.model.Sentenciado
import sspc.gob.mx.psr.model.catalog.Estado
import sspc.gob.mx.psr.model.catalog.Pais
import sspc.gob.mx.psr.repository.FolioRepository
import sspc.gob.mx.psr.services.imp.FolioServiceImp

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FolioServiceSpec extends Specification{

    FolioService folioService = new FolioServiceImp()

    void setup(){
        folioService.folioRepository = Mock(FolioRepository)
    }

    def "Debe testear la generacion de folio"(){
        given:
        Pais mexico  = mexico()

        Estado cdmx = cdmx()
        def localDate = LocalDate.of(1988, Month.APRIL, 16)


        Sentenciado cmd = new Sentenciado()
        cmd.with {
            nombre = 'Alberto'
            apellidoPaterno = 'Hernandez'
            apellidoMaterno = 'Lugo'
            nacionalidad = mexico
            estado = cdmx
            fechaNacimiento = localDate
            sexo = Sexo.MASCULINO
        }

        when:
        1 * folioService.folioRepository.findAllByParams(_ as String, _ as Long,
                _ as String, _ as Character, _ as String) >> []
        def resp = folioService.construirFolio(cmd)

        then:
        assert resp.toString() == 'HELA88041613HMEX000'

    }

    static Pais mexico(){
        Pais mexico  = new Pais()
        mexico.with {
            id = 484
            nombre = 'México'
            alfa3 = 'MEX'
        }
        mexico
    }

    static Estado cdmx(){
        Estado cdmx = new Estado()
        cdmx.with {
            id = 13
            nombre = 'CIUDAD DE MÉXICO'
        }
        cdmx
    }

}






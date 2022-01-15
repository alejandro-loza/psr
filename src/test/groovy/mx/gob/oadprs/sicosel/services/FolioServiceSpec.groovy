package mx.gob.oadprs.sicosel.services

import mx.gob.oadprs.sicosel.services.imp.FolioServiceImp
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FolioServiceSpec extends Specification{

    FolioService folioService = new FolioServiceImp()

    void setup(){
        folioService.folioRepository = Mock(mx.gob.oadprs.sicosel.repository.FolioRepository)
    }

    def "Debe testear la generacion de folio"(){
        given:
        mx.gob.oadprs.sicosel.model.catalog.Pais mexico  = mexico()

        mx.gob.oadprs.sicosel.model.catalog.Estado cdmx = cdmx()
        def localDate = LocalDate.of(1988, Month.APRIL, 16)


        mx.gob.oadprs.sicosel.model.Sentenciado cmd = new mx.gob.oadprs.sicosel.model.Sentenciado()
        cmd.with {
            nombre = 'Alberto'
            apellidoPaterno = 'Hernandez'
            apellidoMaterno = 'Lugo'
            nacionalidad = mexico
            estado = cdmx
            fechaNacimiento = localDate
            sexo = mx.gob.oadprs.sicosel.enums.Sexo.MASCULINO
        }

        when:
        1 * folioService.folioRepository.findAllByParams(_ as String, _ as Long,
                _ as String, _ as Character, _ as String) >> []
        def resp = folioService.construirFolio(cmd)

        then:
        assert resp.toString() == 'HELA88041613HMEX000'

    }

    static mx.gob.oadprs.sicosel.model.catalog.Pais mexico(){
        mx.gob.oadprs.sicosel.model.catalog.Pais mexico  = new mx.gob.oadprs.sicosel.model.catalog.Pais()
        mexico.with {
            id = 484
            nombre = 'México'
            alfa3 = 'MEX'
        }
        mexico
    }

    static mx.gob.oadprs.sicosel.model.catalog.Estado cdmx(){
        mx.gob.oadprs.sicosel.model.catalog.Estado cdmx = new mx.gob.oadprs.sicosel.model.catalog.Estado()
        cdmx.with {
            id = 13
            nombre = 'CIUDAD DE MÉXICO'
        }
        cdmx
    }

}






package sspc.gob.mx.psr.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import sspc.gob.mx.psr.dto.SentenciadoDto
import sspc.gob.mx.psr.enums.Sexo
import sspc.gob.mx.psr.services.SentenciadoService
import sspc.gob.mx.psr.validator.DomicilioValidador
import sspc.gob.mx.psr.validator.SentenciadoValidador

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DomicilioControllerSpec extends Specification {
    public static final int MEXICO_ID = 82


    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()


    def "Deberia crear un domicilio"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)


        and:'a body request'
        DomicilioValidador cmd = new DomicilioValidador()
        cmd.with {
            estadoId = 13
            paisId = MEXICO_ID
            municipioId = 13048
            codigoPostal = '12345'
            colonia = 'Benito Juarez'
            calle = 'Bolevar of bronken dreams'
            numero = 666
            latitud = '19.4326018'
            longitud = '-99.1332049'
            descripcion = 'datos test'
        }

        when:
        def resp = rest.postForObject("http://localhost:${ port }/domicilio", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert it.estado == 'HIDALGO'
            assert it.pais == 'MÉXICO'
            assert it.municipio == 'Pachuca de Soto'
            assert it.colonia == 'Benito Juarez'
            assert it.calle == 'Bolevar of bronken dreams'
            assert it.numero == '666'
            assert it.codigoPostal == '12345'
            assert it.latitud == '19.4326018'
            assert it.longitud == '-99.1332049'
        }

    }



}

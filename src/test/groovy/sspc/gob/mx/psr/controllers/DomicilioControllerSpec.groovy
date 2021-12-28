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

    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

    @Autowired
    SentenciadoService sentenciadoService;

    def "Deberia crear un domicilio"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def senteciado = sentenciadoGuardado()

        and:'a body request'
        DomicilioValidador cmd = new DomicilioValidador()
        cmd.with {
            sentenciadoId = senteciado.id
            estadoId = 13
            paisId = 484
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
            assert it.sentenciadoId
            assert it.estado == 'HIDALGO'
            assert it.pais == 'México'
            assert it.municipio == 'Pachuca de Soto'
            assert it.colonia == 'Benito Juarez'
            assert it.calle == 'Bolevar of bronken dreams'
            assert it.numero == '666'
            assert it.codigoPostal == '12345'
            assert it.latitud == '19.4326018'
            assert it.longitud == '-99.1332049'
        }

    }

    private SentenciadoDto sentenciadoGuardado() {
        SentenciadoValidador cmd = new SentenciadoValidador()
        cmd.with {
            nombre = 'Tomas'
            apellidoPaterno = 'Ràmirez'
            apellidoMaterno = 'Torres'
            nacionalidadId = 484
            estadoId = 13
            documento = 'HELA880416HHGRZL08'
            estadoCivil = 1
            alias = "el pinky"
            otrosNombres = "Enrique Peña"
            fechaNacimiento = LocalDate.of(1988, Month.APRIL, 16)
            ocupacionId = 1
            sexo = Sexo.MASCULINO
            etniaId = 1
            escolaridad = 1
            telefonoFijo = 1234567890
            celular = 1234567890
            correoElectronico = 'juan.antonio.perez.garcia@gmail.com'
        }
        return sentenciadoService.crear(cmd)
    }


}

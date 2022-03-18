package mx.gob.oadprs.sicosel.controllers

import mx.gob.oadprs.sicosel.dto.DomicilioDto
import mx.gob.oadprs.sicosel.dto.FamiliarDto
import mx.gob.oadprs.sicosel.dto.SentenciadoDto
import mx.gob.oadprs.sicosel.enums.Sexo
import mx.gob.oadprs.sicosel.model.Sentenciado
import mx.gob.oadprs.sicosel.services.FamiliarService
import mx.gob.oadprs.sicosel.services.SentenciadoService
import mx.gob.oadprs.sicosel.validator.DomicilioValidador
import mx.gob.oadprs.sicosel.validator.FamiliarValidador
import mx.gob.oadprs.sicosel.validator.SentenciadoValidador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DomicilioControllerSpec extends Specification {
    public static final int MEXICO_ID = 82

    @Value('${local.server.port}')
    int port

    RestTemplate rest = new RestTemplate()

    @Autowired
    SentenciadoService sentenciadoService

    @Autowired
    FamiliarService familiarService

    def "Deberia crear un domicilio"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def senteciado = sentenciadoGuardado()

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
        def resp = rest.postForObject("http://localhost:${port}/sentenciado/" +
                "$senteciado.id/domicilio", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert it.personaId == senteciado.id
            assert it.estado == 'HIDALGO'
            assert it.estadoId == cmd.estadoId
            assert it.pais == 'MÉXICO'
            assert it.paisId == cmd.paisId
            assert it.municipio == 'Pachuca de Soto'
            assert it.municipioId == cmd.municipioId
            assert it.colonia == 'Benito Juarez'
            assert it.calle == 'Bolevar of bronken dreams'
            assert it.numero == '666'
            assert it.codigoPostal == '12345'
            assert it.latitud == '19.4326018'
            assert it.longitud == '-99.1332049'
        }

    }

    def "Deberia consultar un domicilio"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado con domicilio guardado'
        def senteciado = sentenciadoGuardado()
        def domicilio = agregaDomicilioSentenciado(senteciado)

        when:
        def resp = rest.getForEntity("http://localhost:${ port }/sentenciado/$senteciado.id/domicilio", Map)?.body

        then:
        assert UUID.fromString(resp.personaId as String) == domicilio.personaId
    }

    def "Deberia consultar un domicilio de un familiar"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def senteciado = sentenciadoGuardado()

        and:'un familiar guardado'
        def familiar = crearFamiliar(sentenciadoService.busca(UUID.fromString(senteciado.id)))

        agregaDomicilioFamiliar(familiar.id, UUID.fromString(senteciado.id))

        when:
        def resp = rest.getForEntity("http://localhost:${ port }/sentenciado/$senteciado.id" +
                "/familiar/$familiar.id/domicilio", Map)?.body

        then:
        assert resp.personaId == familiar.id.toString()

    }

    def "Deberia crear un domicilio de familiar"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def senteciado = sentenciadoGuardado()

        and:'un familiar guardado'
        def familiar = crearFamiliar(sentenciadoService.busca(UUID.fromString(senteciado.id)))

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
            descripcion = 'datos test'
        }

        when:
        def resp = rest.postForObject("http://localhost:${port}/sentenciado/" +
                "$familiar.sentenciadoId/familiar/$familiar.id/domicilio", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert it.personaId == familiar.id.toString()
            assert it.estado == 'HIDALGO'
            assert it.pais == 'MÉXICO'
            assert it.municipio == 'Pachuca de Soto'
            assert it.colonia == 'Benito Juarez'
            assert it.calle == 'Bolevar of bronken dreams'
            assert it.numero == '666'
            assert it.codigoPostal == '12345'
        }

    }

    private SentenciadoDto sentenciadoGuardado() {
        SentenciadoValidador cmd = new SentenciadoValidador()
        cmd.with {
            nombre = 'Tomas'
            apellidoPaterno = 'Ràmirez'
            apellidoMaterno = 'Torres'
            nacionalidadId = 82
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

    private DomicilioDto agregaDomicilioSentenciado(SentenciadoDto senteciado){
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
        sentenciadoService.agregaDireccion(UUID.fromString(senteciado.id), cmd)
    }

    private DomicilioDto agregaDomicilioFamiliar(UUID familiarId, UUID sentenciadoId){
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
        familiarService.creaDireccion(sentenciadoId, familiarId, cmd)
    }

    private FamiliarDto crearFamiliar(Sentenciado sentenciado){
        FamiliarValidador cmd = new FamiliarValidador()
        cmd.with {
            nombre = 'Chapo Mama'
            apellidoMaterno = 'Loera'
            apellidoPaterno = 'Virginia'
            documento = 'HELA880416HHGRZL08'
            telefonoFijo = 1234567
            celular = 123123
            parentescoId = 2
            nacionalidadId = 82
        }
        return familiarService.crear(cmd, sentenciado)
    }

}

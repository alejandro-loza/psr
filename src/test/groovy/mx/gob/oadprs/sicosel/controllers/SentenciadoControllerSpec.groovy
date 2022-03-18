package mx.gob.oadprs.sicosel.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import mx.gob.oadprs.sicosel.dto.FamiliarDto
import mx.gob.oadprs.sicosel.dto.SentenciadoDto
import mx.gob.oadprs.sicosel.enums.Sexo
import mx.gob.oadprs.sicosel.model.Sentenciado
import mx.gob.oadprs.sicosel.repository.FamiliarRepository
import mx.gob.oadprs.sicosel.repository.FolioRepository
import mx.gob.oadprs.sicosel.repository.SentencedRepository
import mx.gob.oadprs.sicosel.services.FamiliarService
import mx.gob.oadprs.sicosel.services.SentenciadoService
import mx.gob.oadprs.sicosel.validator.FamiliarValidador
import mx.gob.oadprs.sicosel.validator.SentenciadoValidador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SentenciadoControllerSpec extends Specification {

    public static final int MEXICO_ID = 82
    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

    @Autowired
    SentenciadoService sentenciadoService

    @Autowired
    FolioRepository folioRepository

    @Autowired
    FamiliarRepository familiarRepository

    @Autowired
    FamiliarService familiarService

    @Autowired
    SentencedRepository sentencedRepository

    void cleanup(){
        folioRepository.findAll().each {folioRepository.delete(it)}
        familiarRepository.findAll().each {familiarRepository.delete(it)}
        sentencedRepository.findAll().each {sentencedRepository.delete(it)}
    }

    def "Deberia crear un sentenciado"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        Map cmd = [:]
        cmd.with {
            nombre = 'Alejandro'
            apellidoPaterno = 'Ràmirez'
            apellidoMaterno = 'Torres'
            nacionalidadId = MEXICO_ID
            estadoId = 13
            documento = 'HELA880416HHGRZL08'
            estadoCivil = 1
            alias = "el pinky"
            otrosNombres =  "Enrique Peña"
            fechaNacimiento = "1988-04-16"
            ocupacionId = 1
            sexo = Sexo.MASCULINO
            etniaId = 1
            escolaridad = 1
            telefonoFijo =  1234567890
            celular =  1234567890
            correoElectronico = 'juan.antonio.perez.garcia@gmail.com'
        }

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
           assert it.nombre == 'Alejandro'
            assert it.folio == 'RXTA88041613HMEX001'
            assert it.apellidoPaterno == 'Ràmirez'
            assert it.apellidoMaterno == 'Torres'
            assert it.nacionalidad == 'MÉXICO'
            assert it.documento == 'HELA880416HHGRZL08'
            assert it.estadoCivil == 'SOLTERO(A)'
            assert it.alias == "el pinky"
            assert it.otrosNombres ==  "Enrique Peña"
            assert it.fechaNacimiento == '1988-04-16'
            assert it.ocupacion == "EMPLEADO"
            assert it.sexo == 'MASCULINO'
            assert it.etnia == 'AMUZGO'
            assert it.escolaridad == 'SIN ESCOLARIDAD'
            assert it.telefonoFijo ==  '1234567890'
            assert it.celular ==  "1234567890"
            assert it.correoElectronico == 'juan.antonio.perez.garcia@gmail.com'
            assert it.id
            assert it.nacionalidadId == MEXICO_ID
            assert it.estadoId == cmd.estadoId
            assert it.estadoCivilId == cmd.estadoCivil
            assert it.etniaId == cmd.etniaId
            assert it.escolaridadId == cmd.escolaridad

        }

    }

    def "Deberia crear un sentenciado caso de QA"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        Map cmd = [:]
        cmd.with {
            apellidoMaterno = "DDD"
            apellidoPaterno =  "DDD"
            celular =  "9999999999"
            correoElectronico =  "anita19@ggd"
            documento = "AINA941015MDFVYN05"
            escolaridad = "5"
            estadoCivil = "5"
            estadoId = "9"
            etniaId = "3"
            fechaNacimiento = "2022-01-07"
            nacionalidadId =  "82"
            nombre =  "DDDD"
            ocupacionId =  "1"
            sexo = "MASCULINO"
        }

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert it.id
            assert apellidoMaterno == "DDD"
            assert apellidoPaterno ==  "DDD"
            assert celular ==  "9999999999"
            assert correoElectronico ==  "anita19@ggd"
            assert documento == "AINA941015MDFVYN05"
            assert escolaridad == "SECUNDARIA COMPLETA"
            assert estadoCivil == "UNIÓN LIBRE"
            assert estado == "CIUDAD DE MÉXICO"
            assert etnia == "COCHIMI"
            assert fechaNacimiento == "2022-01-07"
            assert nacionalidad ==  "MÉXICO"
            assert nombre ==  "DDDD"
            assert ocupacion ==  "EMPLEADO"
            assert sexo == "MASCULINO"
        }

    }

    def "Deberia crear un sentenciado sin campos opcionales"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        Map cmd = [:]
        cmd.with {
            celular =  "9999999999"
            correoElectronico =  "anita19@ggd"
            documento = "AINA941015MDFVYN05"
            estadoCivil = "5"
            estadoId = "9"
            fechaNacimiento = "2022-01-07"
            nacionalidadId =  "82"
            nombre =  "DDDD"
            sexo = "MASCULINO"
        }

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert it.id
            assert it.folio
            assert celular == "9999999999"
            assert correoElectronico == "anita19@ggd"
            assert documento == "AINA941015MDFVYN05"
            assert estadoCivil == "UNIÓN LIBRE"
            assert estado == "CIUDAD DE MÉXICO"
            assert fechaNacimiento == "2022-01-07"
            assert nacionalidad ==  "MÉXICO"
            assert nombre ==  "DDDD"
            assert sexo == "MASCULINO"
        }

    }

    def "Should not post on invalid args "(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        SentenciadoValidador cmd = new SentenciadoValidador()
        cmd.with {
            apellidoPaterno = 'Pèrez'
            apellidoMaterno = 'Garcìa'
            nacionalidadId = MEXICO_ID
            estadoId = 13
            documento = 'HELA880416HHGRZL08'
            estadoCivil = 1
            alias = "el pinky"
            otrosNombres =  "Enrique Peña"
            fechaNacimiento = LocalDate.of(1988, Month.APRIL, 16)
            ocupacionId = 1
            sexo = Sexo.FEMENINO
            etniaId = 1
            escolaridad =  1
            telefonoFijo =  1234567890
            celular =  1234567890
            correoElectronico = 'juan.antonio.perez.garcia@gmail.com'
        }

        when:
        rest.postForObject("http://localhost:${ port }/sentenciado", new HttpEntity(cmd, headers), Map)

        then:
        thrown HttpClientErrorException.BadRequest
    }

    def "Deberia crear un familiar del sentenciado"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        FamiliarValidador cmd = new FamiliarValidador()
        cmd.with {
            nombre = 'Chapo Mama'
            apellidoMaterno = 'Loera'
            apellidoPaterno = 'Virginia'
            documento = 'HELA880416HHGRZL08'
            telefonoFijo = "1234567"
            celular = "123123"
            parentescoId = 2
            nacionalidadId =  82
        }

        println(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(cmd))

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado/$sentenciado.id/familiar", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert id
            assert sentenciadoId == sentenciado.id
            assert nombre == 'Chapo Mama'
            assert apellidoPaterno == 'Virginia'
            assert apellidoMaterno == 'Loera'
            assert documento == 'HELA880416HHGRZL08'
            assert telefonoFijo == '1234567'
            assert celular == '123123'
            assert parentesco == 'MADRE'
            assert parentescoId == cmd.parentescoId
            assert nacionalidad ==  "MÉXICO"
            assert nacionalidadId ==  cmd.nacionalidadId
        }

    }

    def "Deberia consultar los familiares de un sentenciado"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def sentenciado = sentenciadoGuardado()

        and:'familiares guardados'
        2.times {crearFamiliar(sentenciadoService.busca(UUID.fromString(sentenciado.id)))}

        when:
        def resp = rest.getForEntity("http://localhost:${ port }/sentenciado/$sentenciado.id/familiar", List)?.body

        then:
        assert resp.size() == 2
    }

    def "Deberia consultar un familiar de un sentenciado"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def sentenciado = sentenciadoGuardado()

        and:'familiares guardados'
        def familiar = crearFamiliar(sentenciadoService.busca(UUID.fromString(sentenciado.id)))

        when:
        def resp = rest.getForEntity("http://localhost:${ port }/sentenciado/$sentenciado.id" +
                "/familiar/$familiar.id", Map)?.body

        then:
        assert resp.id == familiar.id.toString()

    }

    def "No deberia crear el familiar cuando faltan campos requeridos y responder Bad Request"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        FamiliarValidador cmd = new FamiliarValidador()
        cmd.with {
            documento = 'HELA880416HHGRZL08'
            telefonoFijo = "1234567"
            celular = "123123"
        }

        println(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(cmd))

        when:
        rest.postForObject("http://localhost:${ port }/sentenciado/$sentenciado.id/familiar", new HttpEntity(cmd, headers), Map)

        then:
        thrown HttpClientErrorException.BadRequest

    }

    def "Deberia  crear un familiar del sentenciado solo con campos requeridos"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        FamiliarValidador cmd = new FamiliarValidador()
        cmd.with {
            nombre = 'CHAPO MAMA'
            apellidoMaterno = "NOYOLA"
            apellidoPaterno = "AVILA"
            parentescoId = 2
            nacionalidadId =  MEXICO_ID
        }

        println(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(cmd))

        when:
        def resp = rest.postForObject("http://localhost:${ port }/sentenciado/$sentenciado.id/familiar", new HttpEntity(cmd, headers), Map)

        then:
        resp.with {
            assert id
            assert sentenciadoId == sentenciado.id
            assert nombre == 'CHAPO MAMA'
            assert apellidoPaterno == 'AVILA'
            assert apellidoMaterno == 'NOYOLA'
            assert documento == null
            assert telefonoFijo == null
            assert celular ==  null
            assert parentesco == 'MADRE'
            assert nacionalidad ==  "MÉXICO"

        }

    }

    def "Debería traer un sentenciado por folio"(){
        given: 'dado un sentenciado guardado'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        when:
        def resp = rest.getForEntity(
                "http://localhost:${ port }/sentenciado/folio/${sentenciado.getFolio()}" , Map)

        then:
        resp.getBody().with {
            assert folio == sentenciado.getFolio()
            assert documento == 'HELA880416HHGRZL08'

        }
    }

    def "Debería traer un sentenciado por id"(){
        given: 'dado un sentenciado guardado'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        when:
        def resp = rest.getForEntity("http://localhost:$port/sentenciado/$sentenciado.id" , Map)

        then:
        resp.getBody().with {
            assert folio == sentenciado.getFolio()
            assert documento == sentenciado.documento
        }
    }

    def "No debería traer un sentenciado por folio con folio erroneo"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        when:
        rest.getForEntity(
                "http://localhost:${port}/sentenciado/" + UUID.randomUUID(), Map)

        then:
        thrown HttpClientErrorException.NotFound
    }

    def "Debería traer un sentenciado por nombre LIKE completo"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        SentenciadoValidador cmd = new SentenciadoValidador()
        cmd.with {
            nombre = 'ANGEL'
            apellidoPaterno = 'MANOLATL'
            apellidoMaterno = 'HERNANDEZ'
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
        def sent = sentenciadoService.crear(cmd)

        def nombreLike = 'ANG'
        def apellidoPaternoLike = 'MAN'
        def apellidoMaternoLike = 'HER'

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?nombre=${nombreLike}" +
                        "&apellidoPaterno=${apellidoPaternoLike}" +
                        "&apellidoMaterno=${apellidoMaternoLike}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert !resp.getBody().isEmpty()
        assert resp.getBody().first().nombre == sent.nombre
    }

    def "Debería traer un sentenciado por alias like"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and: 'dos sentenciado guardado con mismo alias'
        2.times {sentenciadoGuardado()}
        and:'un sentenciado guardado'
        SentenciadoValidador cmd = new SentenciadoValidador()
        cmd.with {
            nombre = 'ANGEL'
            apellidoPaterno = 'MANOLATL'
            apellidoMaterno = 'HERNANDEZ'
            nacionalidadId = 82
            estadoId = 13
            documento = 'HELA880416HHGRZL08'
            estadoCivil = 1
            alias = "the boss"
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
        sentenciadoService.crear(cmd)

        def alias = 'el pinky'

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?alias=${alias}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
        resp.getBody().every {it.nombre == 'Tomas'}
    }

    def "Debería paginar"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        20.times {sentenciadoGuardado()}

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?nombre=Tomas&page=0&size=15", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
        assert resp.getBody().size() == 15

    }

    def "Debería traer un sentenciado por otros nombres like"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        sentenciadoGuardado()
        def otrosNombres = 'Enri'

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?otrosNombres=${otrosNombres}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
    }

    def "Debería traer un sentenciado por nombres padres like"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def senteciado = sentenciadoGuardado()

        and:'un familiar guardado'
        crearFamiliar(sentenciadoService.busca(UUID.fromString(senteciado.id)))


        def nombrePadres = 'Cha'

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?nombrePadres=${nombrePadres}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
        assert resp.getBody().first().nombre == senteciado.nombre
    }

    def "Debería traer un sentenciado por apellido paterno de padres like"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def senteciado = sentenciadoGuardado()

        and:'un familiar guardado'
        crearFamiliar(sentenciadoService.busca(UUID.fromString(senteciado.id)))


        def apellidoPaternoPadres = 'Vir'

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?apellidoPaternoPadres=${apellidoPaternoPadres}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
        assert resp.getBody().first().nombre == 'Tomas'
    }

    def "Debería traer un sentenciado por apellido Materno de padres like"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def senteciado = sentenciadoGuardado()

        and:'un familiar guardado'
        crearFamiliar(sentenciadoService.busca(UUID.fromString(senteciado.id)))

        def apellidoMaternoPadres = 'Loe'

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?apellidoMaternoPadres=${apellidoMaternoPadres}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
        assert resp.getBody().first().nombre == 'Tomas'
    }

    def "Debería traer un sentenciado por pais"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        def pais = '81'

        and:'sentenciados de diferentes paises'
        def sentenciado = sentenciadoGuardado()

        SentenciadoValidador cmd = new SentenciadoValidador()
        cmd.with {
            nombre = 'ANGEL'
            apellidoPaterno = 'MANOLATL'
            apellidoMaterno = 'HERNANDEZ'
            nacionalidadId = 81
            estadoId = 13
            documento = 'HELA880416HHGRZL08'
            estadoCivil = 1
            alias = "the boss"
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
        sentenciadoService.crear(cmd)


        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?paisId=${pais}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
        assert resp.getBody().first().nombre == cmd.nombre
    }

    def "Debería traer un sentenciado por ocupacion"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        def sent = sentenciadoGuardado()
        def ocupacionId = '1'

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?ocupacionId=${ocupacionId}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
        assert resp.getBody().first().nombre == sent.nombre
    }

    def "Debería traer un sentenciado por folio like"(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def sent = sentenciadoGuardado()

        and:'parte de un folio'
        def folio = sent.folio[1..4]

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?folio=${folio}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
        assert resp.getBody().first().folio[1..4] == folio
    }

    def "Debería traer un sentenciado por id "(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        def sent = sentenciadoGuardado()

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?id=${sent.id}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
        assert resp.getBody().first().folio == sent.folio
    }

    def "Debería traer un sentenciado por fecha nacimiento "(){
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and:'un sentenciado guardado'
        sentenciadoGuardado()

        def fecha =  '1988-04-16'

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?fechaNacimiento=${fecha}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp
        assert !resp.getBody().isEmpty()
    }

    def "Debería traer un sentenciado por nombre y apellido"(){
        given: 'dado un sentenciado guardado'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        def sentenciado = sentenciadoGuardado()

        when:
        def resp = rest.getForEntity(
                "http://localhost:${port}/sentenciado?nombre=${sentenciado.getNombre()}" +
                        "&apellidoPaterno=${sentenciado.getApellidoPaterno()}", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert !resp.getBody().isEmpty()
    }

    def "Deberia modificar un sentenciado"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        def sentenciado = sentenciadoGuardado()


        Map cmd = [:]
        cmd.with {
            nombre = 'Alejandro'
            apellidoPaterno = 'Ràmirez'
            apellidoMaterno = 'Torres'
            nacionalidadId = MEXICO_ID
            estadoId = 13
            documento = 'HELA880416HHGRZL08'
            estadoCivil = 1
            alias = "el pinky"
            otrosNombres =  "Enrique Peña"
            fechaNacimiento = "1988-04-16"
            ocupacionId = 1
            sexo = Sexo.MASCULINO
            etniaId = 1
            escolaridad = 1
            telefonoFijo =  1234567890
            celular =  1234567890
            correoElectronico = 'juan.antonio.perez.garcia@gmail.com'
        }

        when:

        def resp = rest.exchange("http://localhost:${ port }/sentenciado/$sentenciado.id",
                HttpMethod.PUT, new HttpEntity(cmd, headers), Map)

        then:
        assert resp.statusCode == HttpStatus.OK
        resp.body.with {
            assert it.nombre == 'Alejandro'
            assert it.folio == sentenciado.folio //TODO REVISAR CASO DE USO UPDATE
            assert it.apellidoPaterno == 'Ràmirez'
            assert it.apellidoMaterno == 'Torres'
            assert it.nacionalidad == 'MÉXICO'
            assert it.documento == 'HELA880416HHGRZL08'
            assert it.estadoCivil == 'SOLTERO(A)'
            assert it.alias == "el pinky"
            assert it.otrosNombres ==  "Enrique Peña"
            assert it.fechaNacimiento == '1988-04-16'
            assert it.ocupacion == "EMPLEADO"
            assert it.sexo == 'MASCULINO'
            assert it.etnia == 'AMUZGO'
            assert it.escolaridad == 'SIN ESCOLARIDAD'
            assert it.telefonoFijo ==  '1234567890'
            assert it.celular ==  "1234567890"
            assert it.correoElectronico == 'juan.antonio.perez.garcia@gmail.com'
            assert it.id == sentenciado.id
        }

    }

    def "Deberia modificar un sentenciado UPDATE SIN OPCIONALES"(){
        given:'a body request'
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        and: 'un sentenciado guardado'
        def sentenciado = sentenciadoGuardado()

        and:'solo campos requeridos'
        Map cmd = [:]
        cmd.with {
            celular =  "9999999999"
            correoElectronico =  "anita19@ggd"
            documento = "AINA941015MDFVYN05"
            estadoCivil = "5"
            estadoId = "9"
            fechaNacimiento = "2022-01-07"
            nacionalidadId =  "82"
            nombre =  "DDDD"
            sexo = "MASCULINO"
        }

        when:

        def resp = rest.exchange("http://localhost:${ port }/sentenciado/$sentenciado.id",
                HttpMethod.PUT, new HttpEntity(cmd, headers), Map)

        then:
        assert resp.statusCode == HttpStatus.OK
        resp.body.with {
            assert nombre ==  "DDDD"
            assert it.folio == sentenciado.folio //TODO REVISAR CASO DE USO UPDATE
            assert it.apellidoPaterno == 'Ràmirez'
            assert it.apellidoMaterno == 'Torres'
            assert it.nacionalidad == 'MÉXICO'
            assert it.estado == "CIUDAD DE MÉXICO"
            assert it.documento == "AINA941015MDFVYN05"
            assert it.estadoCivil == 'UNIÓN LIBRE'
            assert it.alias == "el pinky"
            assert it.otrosNombres ==  "Enrique Peña"
            assert it.fechaNacimiento == "2022-01-07"
            assert it.ocupacion == "EMPLEADO"
            assert it.sexo == 'MASCULINO'
            assert it.etnia == 'AMUZGO'
            assert it.escolaridad == 'SIN ESCOLARIDAD'
            assert it.telefonoFijo ==  '1234567890'
            assert it.celular == "9999999999"
            assert it.correoElectronico == "anita19@ggd"
            assert it.id == sentenciado.id
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

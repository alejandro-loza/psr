package sspc.gob.mx.psr.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import sspc.gob.mx.psr.enums.Sexo
import sspc.gob.mx.psr.validator.SentenciadoValidador

import java.time.LocalDate
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EstadoControllerSpec extends Specification {

    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

    def "Deberia traer todos los estados de la republica"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogos/estado", List)?.body

        then:
        assert resp.size() == 32
        assert resp == estadosRespuesta()
    }

    private static ArrayList<LinkedHashMap<String, Serializable>> estadosRespuesta() {
        [[id: 1, nombre: "AGUASCALIENTES"],
         [id: 2, nombre: "BAJA CALIFORNIA"],
         [id: 3, nombre: "BAJA CALIFORNIA SUR"],
         [id: 4, nombre: "CAMPECHE"], [id: 5, nombre: "COAHUILA DE ZARAGOZA"],
         [id: 6, nombre: "COLIMA"],
         [id: 7, nombre: "CHIAPAS"],
         [id: 8, nombre: "CHIHUAHUA"],
         [id: 9, nombre: "CIUDAD DE MÉXICO"],
         [id: 10, nombre: "DURANGO"],
         [id: 11, nombre: "GUANAJUATO"],
         [id: 12, nombre: "GUERRERO"],
         [id: 13, nombre: "HIDALGO"],
         [id: 14, nombre: "JALISCO"],
         [id: 15, nombre: "ESTADO DE MÉXICO"],
         [id: 16, nombre: "MICHOACÁN DE OCAMPO"],
         [id: 17, nombre: "MORELOS"],
         [id: 18, nombre: "NAYARIR"],
         [id: 19, nombre: "NUEVO LEÓN"],
         [id: 20, nombre: "OAXACA"],
         [id: 21, nombre: "PUEBLA"],
         [id: 22, nombre: "QUERÉTARO"],
         [id: 23, nombre: "QUINTANA ROO"],
         [id: 24, nombre: "SAN LUIS POTOSÍ"],
         [id: 25, nombre: "SINALOA"],
         [id: 26, nombre: "SONORA"],
         [id: 27, nombre: "TABASCO"],
         [id: 28, nombre: "TAMAULIPAS"],
         [id: 29, nombre: "TLAXCALA"],
         [id: 30, nombre: "VERACRUZ"],
         [id: 31, nombre: "YUCATÁN"],
         [id: 32, nombre: "ZACATECAS"]]
    }

}

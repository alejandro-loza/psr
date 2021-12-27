package sspc.gob.mx.psr.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogosControllerSpec extends Specification {

    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

    def "Deberia traer todos los estados de la republica"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/estado", List)?.body

        then:
        assert resp.size() == 32
        assert resp == estadosRespuesta()
    }

    def "Deberia traer todos los estados civiles"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/estadoCivil", List)?.body

        then:
        assert resp.size() == 8
        assert resp == [[id:1, nombre:'SOLTERO(A)'],
                        [id:2, nombre:'CASADO(A)'],
                        [id:3, nombre:'VIUDO(A)'],
                        [id:4, nombre:'CONCUBINATO'],
                        [id:5, nombre:'UNIÓN LIBRE'],
                        [id:6, nombre:'DIVORCIADO(A)'],
                        [id:7, nombre:'SEPARADO'],
                        [id:99, nombre:'OTRO(S)']]
    }

    def "Deberia traer todos las escolaridades"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/escolaridad", List)?.body

        then:
        assert resp.size() == 19
        assert resp == escolaridades()


    }

    def "Deberia traer todos las etnias"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/etnia", List)?.body

        then:
        assert resp.size() > 19
    }

    def "Deberia traer todos las ocupaciones"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/ocupacion", List)?.body

        then:
        assert resp.size() > 19
    }

    private static ArrayList<LinkedHashMap<String, Integer>> escolaridades() {
        [[id: 1, nombre: 'SIN ESCOLARIDAD'],
         [id: 2, nombre: 'PRIMARIA INCOMPLETA'],
         [id: 3, nombre: 'PRIMARIA COMPLETA'],
         [id: 4, nombre: 'SECUNDARIA INCOMPLETA'],
         [id: 5, nombre: 'SECUNDARIA COMPLETA'],
         [id: 6, nombre: 'CARRERA TECNICA INCOMPLETA'],
         [id: 7, nombre: 'CARRERA TECNICA COMPLETA'],
         [id: 8, nombre: 'BACHILLERATO INCOMPLETO'],
         [id: 9, nombre: 'BACHILLERATO COMPLETO'],
         [id: 10, nombre: 'LICENCIATURA INCOMPLETA'],
         [id: 11, nombre: 'LICENCIATURA COMPLETA'],
         [id: 12, nombre: 'MAESTRIA INCOMPLETA'],
         [id: 13, nombre: 'MAESTRIA COMPLETA'],
         [id: 14, nombre: 'DOCTORADO INCOMPLETO'],
         [id: 15, nombre: 'DOCTORADO COMPLETO'],
         [id: 16, nombre: 'ESPECIALIDAD INCOMPLETA'],
         [id: 17, nombre: 'ESPECIALIDAD COMPLETA'],
         [id: 18, nombre: 'ANALFABETO(A)'],
         [id: 99, nombre: 'SIN DATO']]
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

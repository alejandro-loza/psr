package mx.gob.oadprs.sicosel.controllers

import mx.gob.oadprs.sicosel.repository.catalog.MunicipioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogosControllerSpec extends Specification {

    @Value('${local.server.port}')
    int port
    RestTemplate rest = new RestTemplate()

    @Autowired
    MunicipioRepository municipioRepository


    def "Deberia traer todos los estados de la republica"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/estado", List)?.body

        then:
        assert resp.size() == 33
        assert resp == estadosRespuesta()
    }

    def "Deberia traer todos los municipios de la hidalgo"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/estado/13/municipio", List)?.body

        then:
        assert resp.size() == 84

    }


    def "Deberia traer todos los municipios activos de  aguascalientes"(){


        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/estado/1/municipio", List)?.body

        then:
        assert resp == [[id:1001, nombre:'Aguascalientes', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1002, nombre:'Asientos', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1003, nombre:'Calvillo', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1004, nombre:'Cosio', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1005, nombre:'Jesús María', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1006, nombre:'Pabellón de Arteaga', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1007, nombre:'Rincón de Ramos', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1008, nombre:'San José de García', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1009, nombre:'Tepezalá', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1010, nombre:'El Llano', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1011, nombre:'San Francisco de los Romo', descripcion:'', estado:'AGUASCALIENTES', activo:true]]

    }

    def "Deberia traer todos los municipios activos de  aguascalientes excepto la ciudad de aguascalientes"(){

        given:'ciudad de aguascalientes desactivada'
        mx.gob.oadprs.sicosel.model.catalog.Municipio aguascalientesCiudad =municipioRepository.findById(1001L).get()
                aguascalientesCiudad.activo = false
        municipioRepository.save(aguascalientesCiudad)

        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/estado/1/municipio", List)?.body

        then:
        assert resp == [
                        [id:1002, nombre:'Asientos', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1003, nombre:'Calvillo', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1004, nombre:'Cosio', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1005, nombre:'Jesús María', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1006, nombre:'Pabellón de Arteaga', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1007, nombre:'Rincón de Ramos', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1008, nombre:'San José de García', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1009, nombre:'Tepezalá', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1010, nombre:'El Llano', descripcion:'', estado:'AGUASCALIENTES', activo:true],
                        [id:1011, nombre:'San Francisco de los Romo', descripcion:'', estado:'AGUASCALIENTES', activo:true]]

    }

    def "Deberia traer todos los municipios de la guerrero"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/estado/12/municipio", List)?.body

        then:
        assert resp.size() == 81

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
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/etnia", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp.body.size() == 71
    }

    def "Deberia traer todos las ocupaciones"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/ocupacion", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp.body
    }

    def "Deberia traer todos los parentescos"(){
        when:
        def resp = rest.getForEntity("http://localhost:${ port }/catalogo/parentesco", List)

        then:
        assert resp.getStatusCode() == HttpStatus.OK
        assert resp.body
        assert resp.body == [[id:1, nombre:'PADRE'], [id:2, nombre:'MADRE'], [id:3, nombre:'CÓNYUGE']]
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
         [id: 32, nombre: "ZACATECAS"],
         [id: 33, nombre: "OTROS"]]
    }

}

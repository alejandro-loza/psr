package mx.gob.oadprs.sicosel.controllers;

import mx.gob.oadprs.sicosel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/catalogo")
@RestController
public class CatalogosController {

    @Autowired
    EstadoService estadoservicio;

    @Autowired
    EstadoCivilService estadoCivilService;

    @Autowired
    MunicipioService municipioService;

    @Autowired
    EscolaridadService escolaridadService;

    @Autowired
    EtniaService etniaService;

    @Autowired
    OcupacionService ocupacionService;

    @Autowired
    ParentescoService parentescoService;

    @Autowired
    PaisService paisService;

    @GetMapping(path = "/pais", produces = "application/json")
    ResponseEntity paises() {
        return new ResponseEntity<>( paisService.lista(), HttpStatus.OK);
    }

    @GetMapping(path = "/escolaridad", produces = "application/json")
    ResponseEntity escolaridad() {
        return new ResponseEntity<>( escolaridadService.lista(), HttpStatus.OK);
    }

    @GetMapping(path = "/estado", produces = "application/json")
    ResponseEntity estados() {
        return new ResponseEntity<>( estadoservicio.lista(), HttpStatus.OK);
    }

    @GetMapping(path="/estado/{estadoId}/municipio", produces = "application/json")
    ResponseEntity municipios(@PathVariable("estadoId") Long estadoId) {
        return new ResponseEntity<>( municipioService.muncipiosPorEstado(estadoservicio.busca(estadoId)), HttpStatus.OK);
    }

    @GetMapping(path = "/estadoCivil", produces = "application/json")
    ResponseEntity estadoCivil() {
        return new ResponseEntity<>( estadoCivilService.lista(), HttpStatus.OK);
    }

    @GetMapping(path = "/etnia", produces = "application/json")
    ResponseEntity etnias() {
        return new ResponseEntity<>( etniaService.lista(), HttpStatus.OK);
    }

    @GetMapping(path = "/ocupacion", produces = "application/json")
    ResponseEntity ocupaciones() {
        return new ResponseEntity<>( ocupacionService.lista(), HttpStatus.OK);
    }

    @GetMapping(path = "/parentesco", produces = "application/json")
    ResponseEntity parentescos() {
        return new ResponseEntity<>( parentescoService.lista(), HttpStatus.OK);
    }


}

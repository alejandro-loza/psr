package sspc.gob.mx.psr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sspc.gob.mx.psr.dto.GreetingDto;
import sspc.gob.mx.psr.services.EscolaridadService;
import sspc.gob.mx.psr.services.EstadoService;
import sspc.gob.mx.psr.services.imp.EstadoServiceImp;

@RequestMapping("/catalogo")
@RestController
public class CatalogosController {

    @Autowired
    EstadoService estadoservicio;

    @Autowired
    EscolaridadService escolaridadService;

    @GetMapping(path = "/escolaridad", produces = "application/json")
    ResponseEntity escolaridad() {
        return new ResponseEntity<>( escolaridadService.lista(), HttpStatus.OK);
    }

    @GetMapping(path = "/estado", produces = "application/json")
    ResponseEntity estados() {
        return new ResponseEntity<>( estadoservicio.lista(), HttpStatus.OK);
    }

}

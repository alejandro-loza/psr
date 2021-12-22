package sspc.gob.mx.psr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sspc.gob.mx.psr.services.PaisService;

@RequestMapping("catalogos/pais")
@RestController
public class PaisesController {

    @Autowired
    PaisService paisService;

    @GetMapping( produces = "application/json")
    ResponseEntity estados() {
        return new ResponseEntity<>( paisService.lista(), HttpStatus.OK);
    }

}

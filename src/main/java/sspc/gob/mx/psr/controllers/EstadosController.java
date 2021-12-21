package sspc.gob.mx.psr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sspc.gob.mx.psr.dto.GreetingDto;
import sspc.gob.mx.psr.services.imp.EstadoServiceImp;

@RequestMapping("/estado")
@RestController
public class EstadosController {

    @Autowired
    EstadoServiceImp estadoservicio;



    @GetMapping( produces = "application/json")
    ResponseEntity estados() {
        return new ResponseEntity<>( estadoservicio.estados()
                , HttpStatus.OK);
    }


}

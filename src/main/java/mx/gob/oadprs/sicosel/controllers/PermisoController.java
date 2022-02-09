package mx.gob.oadprs.sicosel.controllers;

import mx.gob.oadprs.sicosel.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermisoController {

    @Autowired
    PermisoService permisoService;

    @GetMapping(path = "/login/permisos/{rol}", produces = "application/json")
    public ResponseEntity obtienePermisos(@PathVariable("rol") String rol) {
        return new ResponseEntity<>(permisoService.getPermisos(rol), HttpStatus.OK);
    }
}
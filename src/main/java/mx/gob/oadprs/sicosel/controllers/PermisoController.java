package mx.gob.oadprs.sicosel.controllers;

import mx.gob.oadprs.sicosel.dto.PermisoDto;
import mx.gob.oadprs.sicosel.services.MunicipioService;
import mx.gob.oadprs.sicosel.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermisoController {

    @Autowired
    PermisoService permisoService;

    @GetMapping(path = "/login/permisos/{rol}", produces = "application/json")
    public ResponseEntity obtienePermisos(@PathVariable("rol") String rol) {
        return new ResponseEntity<>(permisoService.getPermisos(rol), HttpStatus.OK);
    }
}
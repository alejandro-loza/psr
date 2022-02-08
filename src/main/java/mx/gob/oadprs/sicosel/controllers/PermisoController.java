package mx.gob.oadprs.sicosel.controllers;

import mx.gob.oadprs.sicosel.dto.PermisoDto;
import mx.gob.oadprs.sicosel.services.PermisoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permisos")
public class PermisoController {

    PermisoService permisoService;

    @GetMapping("/permisos")
      ResponseEntity <List> findByRol(@RequestParam String rol) throws Exception {
        return new ResponseEntity( permisoService.getPermisos(rol), HttpStatus.OK);
    }}
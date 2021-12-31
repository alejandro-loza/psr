package sspc.gob.mx.psr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.model.catalog.Municipio;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.services.*;
import sspc.gob.mx.psr.validator.DomicilioValidador;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/domicilio")
@RestController
public class DomicilioController {

    @Autowired
    PaisService paisService;

    @Autowired
    MunicipioService municipioService;

    @Autowired
    SentenciadoService sentenciadoService;

    @Autowired
    DomicilioService domicilioService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity crear(@RequestBody @Valid DomicilioValidador validador) throws Exception {
        Pais pais = paisService.busca(validador.getPaisId());
        Municipio municipio = municipioService.busca(validador.getMunicipioId());
        return new ResponseEntity<>( domicilioService.crear(validador, municipio, pais), HttpStatus.OK);
    }
}

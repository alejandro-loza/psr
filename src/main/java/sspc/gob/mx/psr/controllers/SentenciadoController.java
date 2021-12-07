package sspc.gob.mx.psr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sspc.gob.mx.psr.services.SentenciadoService;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

import javax.validation.Valid;

@RequestMapping("/sentenciado")
@RestController
public class SentenciadoController {

    @Autowired
    SentenciadoService sentenciadoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity crear(@RequestBody @Valid SentenciadoValidador validador) {
        return new ResponseEntity<>(sentenciadoService.crear(validador), HttpStatus.OK);
    }
}

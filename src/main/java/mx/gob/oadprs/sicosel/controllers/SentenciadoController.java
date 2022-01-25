package mx.gob.oadprs.sicosel.controllers;

import mx.gob.oadprs.sicosel.model.Sentenciado;
import mx.gob.oadprs.sicosel.services.FamiliarService;
import mx.gob.oadprs.sicosel.services.SentenciadoService;
import mx.gob.oadprs.sicosel.validator.FamiliarValidador;
import mx.gob.oadprs.sicosel.validator.SentenciadoValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/sentenciado")
@RestController
public class SentenciadoController {

    @Autowired
    SentenciadoService sentenciadoService;

    @Autowired
    FamiliarService familiarService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity crear(@RequestBody @Valid SentenciadoValidador validador) throws Exception {
        return new ResponseEntity<>( sentenciadoService.crear(validador), HttpStatus.OK);
    }

    @PostMapping(path="/{sentenciadoId}/familiar", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity crearFamiliar(@RequestBody @Valid FamiliarValidador validador,
                                 @PathVariable("sentenciadoId") UUID sentenciadoId) throws Exception {
        Sentenciado sentenciado = sentenciadoService.busca(sentenciadoId);
        return new ResponseEntity<>( familiarService.crear(validador, sentenciado), HttpStatus.OK);
    }
}


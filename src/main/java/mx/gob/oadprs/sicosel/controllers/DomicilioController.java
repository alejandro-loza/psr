package mx.gob.oadprs.sicosel.controllers;

import mx.gob.oadprs.sicosel.services.FamiliarService;
import mx.gob.oadprs.sicosel.services.SentenciadoService;
import mx.gob.oadprs.sicosel.validator.DomicilioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/sentenciado")
@RestController
public class DomicilioController {



    @Autowired
    SentenciadoService sentenciadoService;

    @Autowired
    FamiliarService familiarService;

    @PostMapping(path="/{sentenciadoId}/domicilio", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity sentenciadoDomicilio(@RequestBody @Valid DomicilioValidador validador,
                                        @PathVariable("sentenciadoId") UUID sentenciadoId) throws Exception {
        return new ResponseEntity<>(sentenciadoService.agregaDireccion(sentenciadoId,validador), HttpStatus.OK);
    }

    @PostMapping(path="/{sentenciadoId}/familiar/{familiarId}/domicilio", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity familiarDomicilio(@RequestBody @Valid DomicilioValidador validador,
                                     @PathVariable("sentenciadoId") UUID sentenciadoId,
                                     @PathVariable("familiarId") UUID familiarId) throws Exception {

        return new ResponseEntity<>( familiarService.creaDireccion(sentenciadoId,familiarId, validador) , HttpStatus.OK);
    }
}

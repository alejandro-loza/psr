package sspc.gob.mx.psr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sspc.gob.mx.psr.dto.DomicilioDto;
import sspc.gob.mx.psr.model.Domicilio;
import sspc.gob.mx.psr.model.Familiar;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.model.catalog.Municipio;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.services.*;
import sspc.gob.mx.psr.validator.DomicilioValidador;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/sentenciado")
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

    @Autowired
    FamiliarService familiarService;

    @PostMapping(path="/{sentenciadoId}/domicilio", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity sentenciadoDomicilio(@RequestBody @Valid DomicilioValidador validador,
                                        @PathVariable("sentenciadoId") UUID sentenciadoId) throws Exception {

        Sentenciado sentenciado = sentenciadoService.busca(sentenciadoId);
        Domicilio domicilio = domicilioService.crear(validador,
                municipioService.busca(validador.getMunicipioId()),
                paisService.busca(validador.getPaisId()));

        sentenciadoService.creaDireccion(sentenciado,domicilio);//todo revisar para historico en update

        return new ResponseEntity<>(new DomicilioDto(domicilio, sentenciado.getId()), HttpStatus.OK);
    }

    @PostMapping(path="/{sentenciadoId}/familiar/{familiarId}/domicilio", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity familiarDomicilio(@RequestBody @Valid DomicilioValidador validador,
                                     @PathVariable("sentenciadoId") UUID sentenciadoId,
                                     @PathVariable("familiarId") UUID familiarId) throws Exception {

        Sentenciado sentenciado = sentenciadoService.busca(sentenciadoId);
        Familiar familiar = familiarService.buscaFamiliarSentenciado(sentenciado, familiarId);
        Domicilio domicilio = domicilioService.crear(validador,
                municipioService.busca(validador.getMunicipioId()),
                paisService.busca(validador.getPaisId()));

        familiarService.creaDireccion(familiar, domicilio);//todo revisar para historico en update

        return new ResponseEntity<>(new DomicilioDto(domicilio, familiar.getId()), HttpStatus.OK);
    }
}

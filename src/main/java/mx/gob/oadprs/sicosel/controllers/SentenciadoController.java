package mx.gob.oadprs.sicosel.controllers;

import mx.gob.oadprs.sicosel.model.Sentenciado;
import mx.gob.oadprs.sicosel.services.ConsultaSentenciadoService;
import mx.gob.oadprs.sicosel.services.FamiliarService;
import mx.gob.oadprs.sicosel.utils.SentenciadoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.gob.oadprs.sicosel.dto.SentenciadoDto;
import mx.gob.oadprs.sicosel.services.SentenciadoService;
import mx.gob.oadprs.sicosel.validator.FamiliarValidador;
import mx.gob.oadprs.sicosel.validator.SentenciadoValidador;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sentenciado")
@RestController
public class SentenciadoController {

    @Autowired
    SentenciadoService sentenciadoService;

    @Autowired
    ConsultaSentenciadoService consultaService;

    @Autowired
    FamiliarService familiarService;

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

    @GetMapping("/folio/{folio}")
    ResponseEntity buscaPorFolio(@PathVariable("folio") String folio) throws Exception {
        return new ResponseEntity<>(new SentenciadoDto(sentenciadoService.buscaPorFolio(folio)) , HttpStatus.OK);
    }

    @GetMapping(path = "/{sentenciadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity buscaPorId(@PathVariable("sentenciadoId") UUID sentenciadoId) throws Exception {
        return new ResponseEntity<>( new SentenciadoDto(sentenciadoService.busca(sentenciadoId)) , HttpStatus.OK);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity buscaPorNombreCompleto(
            @RequestParam(required = false) Optional<String> nombre,
            @RequestParam(required = false) Optional<String> apellidoPaterno,
            @RequestParam(required = false) Optional<String> apellidoMaterno,
            @RequestParam(required = false) Optional<String> alias,
            @RequestParam(required = false) Optional<String> otrosNombres,
            @RequestParam(required = false) Optional<String> nombrePadres,
            @RequestParam(required = false) Optional<String> apellidoPaternoPadres,
            @RequestParam(required = false) Optional<String> apellidoMaternoPadres,
            @RequestParam(required = false) Optional<Long> paisId,
            @RequestParam(required = false) Optional<Long> ocupacionId,
            @RequestParam(required = false) Optional<String> folio,
            @RequestParam(required = false) Optional<String> fechaNacimiento,
            @RequestParam(required = false) Optional<UUID> id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws Exception {

        SentenciadoCriteria criteriosBusqueda = SentenciadoCriteria.builder()
                .nombre(nombre)
                .apellidoMaterno(apellidoMaterno)
                .apellidoPaterno(apellidoPaterno)
                .alias(alias)
                .otrosNombres(otrosNombres)
                .nombrePadres(nombrePadres)
                .apellidoPaternoPadres(apellidoPaternoPadres)
                .apellidoMaternoPadres(apellidoMaternoPadres)
                .nacionalidad(paisId)
                .ocupacion(ocupacionId)
                .folio(folio)
                .fechaNacimiento(fechaNacimiento)
                .id(id)
                .build();

        return new ResponseEntity<>(consultaService.consulta(criteriosBusqueda, PageRequest.of(page, size)), HttpStatus.OK);
    }

}


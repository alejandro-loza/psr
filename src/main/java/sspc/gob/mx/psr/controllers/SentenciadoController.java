package sspc.gob.mx.psr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sspc.gob.mx.psr.dto.SentenciadoDto;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.services.FamiliarService;
import sspc.gob.mx.psr.services.SentenciadoService;
import sspc.gob.mx.psr.validator.FamiliarValidador;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/sentenciado")
@RestController
public class SentenciadoController {

    @Autowired
    SentenciadoService sentenciadoService;

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

    @GetMapping("/{folio}")
    ResponseEntity buscaPorFolio(@PathVariable("folio") String folio) throws Exception {
        return new ResponseEntity<>(new SentenciadoDto(sentenciadoService.buscaPorFolio(folio)) , HttpStatus.OK);
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity buscaPorNombreCompleto(@RequestParam(required = true) String nombre,
                                         @RequestParam(required = true) String apellidoPaterno,
                                         @RequestParam(required = false) String apellidoMaterno) throws Exception {
        return (apellidoMaterno != null) ?
                busquedaNombreCompleto(nombre, apellidoPaterno, apellidoMaterno):
                busquedaNombreApellidoPaterno(nombre, apellidoPaterno);
    }

    private ResponseEntity<List <SentenciadoDto>> busquedaNombreCompleto(
            String nombre, String apellidoPaterno, String apellidoMaterno) throws Exception {
        return new ResponseEntity<>(sentenciadoService.buscaPorNombreCompleto(
                nombre, apellidoPaterno, apellidoMaterno), HttpStatus.OK);
    }

    private ResponseEntity<List <SentenciadoDto>> busquedaNombreApellidoPaterno(String nombre, String apellidoPaterno) throws Exception {
        return new ResponseEntity<>(sentenciadoService.buscaPorNombreApellidoPaterno(
                nombre, apellidoPaterno), HttpStatus.OK);
    }
}


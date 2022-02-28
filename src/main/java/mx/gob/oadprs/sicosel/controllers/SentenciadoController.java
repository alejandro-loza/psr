package mx.gob.oadprs.sicosel.controllers;

import mx.gob.oadprs.sicosel.model.Sentenciado;
import mx.gob.oadprs.sicosel.services.FamiliarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.gob.oadprs.sicosel.dto.SentenciadoDto;
import mx.gob.oadprs.sicosel.services.SentenciadoService;
import mx.gob.oadprs.sicosel.validator.FamiliarValidador;
import mx.gob.oadprs.sicosel.validator.SentenciadoValidador;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/folio/{folio}")
    ResponseEntity buscaPorFolio(@PathVariable("folio") String folio) throws Exception {
        return new ResponseEntity<>(new SentenciadoDto(sentenciadoService.buscaPorFolio(folio)) , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity buscaPorId(@PathVariable("folio") UUID sentenciadoId) throws Exception {
        return new ResponseEntity<>(new SentenciadoDto(sentenciadoService.busca(sentenciadoId)) , HttpStatus.OK);
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


package sspc.gob.mx.psr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sspc.gob.mx.psr.services.SentencedService;
import sspc.gob.mx.psr.validator.SentencedValidator;

import javax.validation.Valid;

@RequestMapping("/sentenced")
@RestController
public class SentencedController {

    @Autowired
    SentencedService sentencedService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity create (@RequestBody @Valid SentencedValidator validator) {
        return new ResponseEntity<>(sentencedService.create(validator), HttpStatus.OK);
    }
}

package sspc.gob.mx.psr.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sspc.gob.mx.psr.validator.SentencedValidator;

import javax.validation.Valid;

@RequestMapping("/sentenced")
@RestController
public class SentencedController {
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity create (@RequestBody @Valid SentencedValidator validator) {
        return new ResponseEntity<>(validator, HttpStatus.OK);
    }
}

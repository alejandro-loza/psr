package sspc.gob.mx.psr.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sspc.gob.mx.psr.dto.GreetingDto;
import sspc.gob.mx.psr.validator.GreetingValidator;

import javax.validation.Valid;

@RequestMapping("/greetings")
@RestController
public class GreetingController {
    @GetMapping( produces = "application/json")
    ResponseEntity greet() {
        return new ResponseEntity<>(new GreetingDto("hello from spring boot"), HttpStatus.OK);
    }

    @GetMapping(path="/{name}", produces = "application/json")
    ResponseEntity greetName(@PathVariable("name") String name) {
        return new ResponseEntity<>(new GreetingDto("hello " + name), HttpStatus.OK);
    }

    @GetMapping("/query")
    ResponseEntity query(@RequestParam String id) {
        return new ResponseEntity<>( new GreetingDto("ID: " + id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity create (@RequestBody @Valid GreetingValidator validator) {
        return new ResponseEntity<>(validator, HttpStatus.OK);
    }
}

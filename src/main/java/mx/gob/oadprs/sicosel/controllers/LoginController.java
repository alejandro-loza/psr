package mx.gob.oadprs.sicosel.controllers;


import mx.gob.oadprs.sicosel.services.LoginService;
import mx.gob.oadprs.sicosel.validator.LoginRequest;
import mx.gob.oadprs.sicosel.validator.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(path="/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRequest> prsLogin(@RequestBody @Valid LoginRequest cmd) throws Exception {
        return new ResponseEntity<>( loginService.prsLogin(cmd), HttpStatus.OK);
    }

}
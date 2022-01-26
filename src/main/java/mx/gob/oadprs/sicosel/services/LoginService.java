package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.validator.LoginRequestValidador;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginService {
    ResponseEntity<Map> login(LoginRequestValidador loginRequestValidador);
}

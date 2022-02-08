package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.LoginDto;
import mx.gob.oadprs.sicosel.validator.LoginRequestValidador;
import mx.gob.oadprs.sicosel.validator.PermisoRequestValidador;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginService {
    ResponseEntity<LoginDto> login(LoginRequestValidador loginRequestValidador);
}

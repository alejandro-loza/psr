package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.LoginDto;
import mx.gob.oadprs.sicosel.validator.LoginRequestValidador;

public interface LoginService {
    LoginDto login(LoginRequestValidador loginRequestValidador);
}

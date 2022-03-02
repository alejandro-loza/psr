package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.validator.LoginRequest;
import mx.gob.oadprs.sicosel.validator.UserRequest;

public interface LoginService {
    UserRequest prsLogin(LoginRequest loginRequest) throws Exception;

}

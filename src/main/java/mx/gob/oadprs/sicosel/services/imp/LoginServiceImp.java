package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.services.LoginService;
import mx.gob.oadprs.sicosel.validator.LoginRequest;
import mx.gob.oadprs.sicosel.validator.LoginRequestValidador;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LoginServiceImp implements LoginService {
    public static final String NOMBRE_SISTEMA = "SICOSEL";
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${clientes.autenticacion}")
    private String url;

    @Override
    public ResponseEntity<Map> login(LoginRequestValidador loginRequestValidador) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Get-JWT", "true");
        LoginRequest cmd = new LoginRequest();
        cmd.setUsuario(loginRequestValidador.getUsuario());
        cmd.setContrasenia(loginRequestValidador.getContrasenia());
        cmd.setSistema(NOMBRE_SISTEMA);

        return restTemplate.exchange(url + "/api/seguridad/autenticacion/",
                HttpMethod.POST, new HttpEntity(cmd, headers), Map.class);

    }

}

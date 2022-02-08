package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.LoginDto;
import mx.gob.oadprs.sicosel.services.LoginService;
import mx.gob.oadprs.sicosel.validator.LoginRequest;
import mx.gob.oadprs.sicosel.validator.LoginRequestValidador;
import mx.gob.oadprs.sicosel.validator.PermisoRequestValidador;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LoginServiceImp implements LoginService {
    public static final String NOMBRE_SISTEMA = "SICOSEL";
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${clientes.autenticacion}")
    private String url;

    @Override
    public ResponseEntity<LoginDto> login(LoginRequestValidador loginRequestValidador) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Get-JWT", "true");
        LoginRequest cmd = new LoginRequest();
        cmd.setUsuario(loginRequestValidador.getUsuario());
        cmd.setContrasenia(loginRequestValidador.getContrasenia());
        cmd.setSistema(NOMBRE_SISTEMA);

        ResponseEntity<LoginDto> responseEntity;

            responseEntity = restTemplate.exchange(url + "/api/seguridad/autenticacion/",
                    HttpMethod.POST, new HttpEntity(cmd, headers), LoginDto.class);

            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                try {
                    System.out.println("lectura correcta "+ responseEntity.getBody());
                //    jsonObject = new JSONObject(responseEntity.getBody());
                } catch (Exception e) {
                    throw new RuntimeException("JSONException occurred");
                }
            }
        return responseEntity;
}

}

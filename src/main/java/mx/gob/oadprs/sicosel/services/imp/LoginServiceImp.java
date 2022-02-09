package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.LoginDto;
import mx.gob.oadprs.sicosel.services.LoginService;
import mx.gob.oadprs.sicosel.validator.LoginRequest;
import mx.gob.oadprs.sicosel.validator.LoginRequestValidador;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImp implements LoginService {
    public static final String NOMBRE_SISTEMA = "SICOSEL";
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${clientes.autenticacion}")
    private String url;

    @Override
    public LoginDto login(LoginRequestValidador loginRequestValidador) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Get-JWT", "true");
        LoginRequest cmd = new LoginRequest();
        cmd.setUsuario(loginRequestValidador.getUsuario());
        cmd.setContrasenia(loginRequestValidador.getContrasenia());
        cmd.setSistema(NOMBRE_SISTEMA);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url + "/api/seguridad/autenticacion/",
                    HttpMethod.POST, new HttpEntity(cmd, headers), Map.class);
        LoginDto loginDto = new LoginDto();
            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                try {
                    loginDto.setUsuario(responseEntity.getBody().get("usuario").toString());
                    loginDto.setSistema(responseEntity.getBody().get("sistema").toString());
                    loginDto.setRoles((List<String>) responseEntity.getBody().get("roles"));
                    loginDto.setToken(String.valueOf(responseEntity.getHeaders().get("X-JWT")));
                } catch (Exception e) {
                    throw new RuntimeException("JSONException occurred");
                    //System.out.println(" "+ responseEntity.getBody());
                }
            }
        return loginDto;
}

}

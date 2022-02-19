package mx.gob.oadprs.sicosel.services.imp;


import mx.gob.oadprs.sicosel.services.LoginService;
import mx.gob.oadprs.sicosel.validator.LoginRequest;
import mx.gob.oadprs.sicosel.validator.LoginRequestValidador;
import mx.gob.oadprs.sicosel.validator.UserRequest;
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
    public UserRequest prsLogin(LoginRequest loginRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Get-JWT", "true");
        LoginRequestValidador cmd = new LoginRequestValidador();
        cmd.setUsuario(loginRequest.getUsuario());//"accesod.infotec@oadprs.gob.mx");
        cmd.setContrasenia("MXFhejJ3c1g=");
        cmd.setSistema(NOMBRE_SISTEMA);

        System.out.println(" loginRequest "+loginRequest);
        System.out.println(" cmd "+cmd);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(url + "/api/seguridad/autenticacion/",
                HttpMethod.POST, new HttpEntity(cmd, headers), Map.class);
        UserRequest userRequest = new UserRequest();
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            try {
                userRequest.setUser(loginRequest.getUsuario());
                userRequest.setPwd(loginRequest.getContrasenia());
                userRequest.setToken(String.valueOf(responseEntity.getHeaders().get("X-JWT").get(0)));
            } catch (Exception e) {
                throw new RuntimeException("JSONException occurred");
            }
        }
        System.out.println(" response "+ responseEntity.getBody());
        System.out.println(" "+ userRequest);
        return userRequest;
    }


}


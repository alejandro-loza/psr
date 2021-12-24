package sspc.gob.mx.psr.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Scope("singleton")
@Component("altisonantes")
public class Altisonantes {

    private static final String FILE_PATH = "altisonantes.json";
    private static Altisonantes instancia;
    private final Map<String, String> altisonantes;

    private Altisonantes(Map<String, String> altisonantes) {
        this.altisonantes = altisonantes;
    }

    public static Map<String, String> listado() throws IOException {
        if(instancia == null){
            instancia = new Altisonantes(leeArchivo());
        }
        return instancia.getAltisonantes();
    }

    private Map<String, String> getAltisonantes() {
        return altisonantes;
    }

    private static Map<String, String> leeArchivo() throws IOException {
        return new ObjectMapper().readValue(new ClassPathResource(FILE_PATH).getInputStream(), Map.class);
    }

}

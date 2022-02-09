package mx.gob.oadprs.sicosel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.gob.oadprs.sicosel.model.Permiso;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    public String usuario;
    public String sistema;
    public List<String> roles;
    public String token;
}

package mx.gob.oadprs.sicosel.validator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginRequestValidador {
    @NotBlank
    @NonNull
    private String usuario;
    @NotBlank
    @NonNull
    private String contrasenia;
}

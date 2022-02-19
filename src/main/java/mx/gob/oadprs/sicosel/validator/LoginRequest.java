package mx.gob.oadprs.sicosel.validator;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Setter
@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    @NonNull
    private String usuario;
    @NotBlank
    @NonNull
    private String contrasenia;
}

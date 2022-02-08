package mx.gob.oadprs.sicosel.validator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PermisoRequestValidador {
    @NotBlank
    @NonNull
    private String rol;
}

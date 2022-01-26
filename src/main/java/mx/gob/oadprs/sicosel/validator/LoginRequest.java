package mx.gob.oadprs.sicosel.validator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class LoginRequest extends LoginRequestValidador {

    private String sistema;

}

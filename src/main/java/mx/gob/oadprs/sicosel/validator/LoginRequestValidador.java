package mx.gob.oadprs.sicosel.validator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class LoginRequestValidador extends LoginRequest {

    private String sistema;

}

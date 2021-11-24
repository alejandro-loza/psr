package sspc.gob.mx.psr.validator;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GreetingValidator {
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    String alias;
}

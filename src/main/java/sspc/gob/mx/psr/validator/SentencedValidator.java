package sspc.gob.mx.psr.validator;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@RequiredArgsConstructor
@Getter
@Setter
public class SentencedValidator {
    @NotNull(message= "sentenced.name.null")
    @NotBlank
    @Size(min = 1, max = 100)
    String name;

    @NotBlank
    @Size(min = 1, max = 100)
    String firstLastName;

    @NotBlank
    @Size(min = 1, max = 100)
    String secondLastName;

    @Size(min = 1, max = 100)
    @NotNull
    @NotBlank
    String nationality;

    @NotNull
    @NotBlank
    @Size(min = 18, max = 18)
    String curp;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    String maritalStatus;

    @NotBlank
    @Size(min = 1, max = 100)
    String alias;

    @NotBlank
    @Size(min = 1, max = 100)
    String otherNames;

    @Digits(integer = 50, fraction = 0)
    Long birthDate;

    @NotBlank
    @Size(min = 1, max = 100)
    String occupation;

    @NotNull
    @NotBlank
    String gender;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    String ethnicity;

    @NotNull
    @NotBlank
    @NotBlank
    @Size(min = 1, max = 50)
    String schooling;

    @NotNull
    @Digits(integer = 10, fraction = 0)
    Long homePhone;

    @NotNull
    @Digits(integer = 10, fraction = 0)
    Long mobilePhone;

    @Email
    String  email;
}

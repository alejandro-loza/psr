package sspc.gob.mx.psr.validator;

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
    String firstName;

    @NotBlank
    @Size(min = 1, max = 100)
    String lastName;

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
    @Size(min = 1, max = 50)
    String schooling;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    String homePhone;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    String mobilePhone;

    @Email
    @NotNull
    @NotBlank
    String  email;
}

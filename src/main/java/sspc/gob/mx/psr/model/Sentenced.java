package sspc.gob.mx.psr.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.UUID;


@Data
@Builder
@Entity
public class Sentenced extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message= "sentenced.name.null")
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @Size(min = 1, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 100)
    private String lastName;

    @Size(min = 1, max = 100)
    @NotNull
    @NotBlank
    private String nationality;

    @NotNull
    @NotBlank
    @Size(min = 18, max = 18)
    private String curp;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String folio;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String maritalStatus;

    @NotBlank
    @Size(min = 1, max = 100)
    private String alias;

    @NotBlank
    @Size(min = 1, max = 100)
    private String otherNames;

    private Date birthDate;

    @NotBlank
    @Size(min = 1, max = 100)
    private String occupation;

    @NotNull
    @NotBlank
    private String gender;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    private String ethnicity;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String schooling;

    @NotNull
    private String homePhone;

    @NotNull
    private String mobilePhone;

    @NotBlank
    @NotBlank
    private String  email;

}

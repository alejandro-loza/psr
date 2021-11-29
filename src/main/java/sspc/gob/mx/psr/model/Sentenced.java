package sspc.gob.mx.psr.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;


@Data
@Builder
@Entity
public class Sentenced extends BaseEntity {//TODO the folioooo¡¡¡ check for the dateCreated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @NotBlank
    @Size(min = 1, max = 50)
    private String schooling;

    @NotNull
    private Long homePhone;

    @NotNull
    private Long mobilePhone;

    private String  email;




}

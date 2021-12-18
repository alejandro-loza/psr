package sspc.gob.mx.psr.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Builder
@Entity
public class Sentenciado extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message= "sentenced.name.null")
    @NotBlank
    @Size(min = 1, max = 100)
    private String nombre;

    @NotBlank
    @Size(min = 1, max = 100)
    private String apellidoPaterno;

    @NotBlank
    @Size(min = 1, max = 100)
    private String apellidoMaterno;

    @Size(min = 1, max = 100)
    @NotNull
    @NotBlank
    private String nacionalidad;

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
    private String estadoCivil;

    @NotBlank
    @Size(min = 1, max = 100)
    private String alias;

    @NotBlank
    @Size(min = 1, max = 100)
    private String otrosNombres;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate fechaNacimiento;

    @NotBlank
    @Size(min = 1, max = 100)
    private String ocupacion;

    @NotNull
    @NotBlank
    private String sexo;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    private String etnia;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String escolaridad;

    @NotNull
    private String telefonoFijo;

    @NotNull
    private String celular;

    @NotBlank
    @NotBlank
    private String  email;

}

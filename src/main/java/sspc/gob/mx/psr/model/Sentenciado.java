package sspc.gob.mx.psr.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sspc.gob.mx.psr.enums.Sexo;
import sspc.gob.mx.psr.model.catalog.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name="pais_id")
    private Pais nacionalidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name="estado_id")
    private Estado estado;

    @NotNull
    @NotBlank
    @Size(min = 18, max = 18)
    private String curp;

    @NotNull
    @ManyToOne
    @JoinColumn(name="folio_id")
    private Folio folio;

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

    @NotNull
    @ManyToOne
    @JoinColumn(name="ocupacion_id")
    private Ocupacion ocupacion;

    @Enumerated(EnumType.STRING)
    @Column(name="sexo")
    private Sexo sexo;

    @NotNull
    @ManyToOne
    @JoinColumn(name="etnia_id")
    private Etnia etnia;

    @NotNull
    @ManyToOne
    @JoinColumn(name="escolaridad_id")
    private Escolaridad escolaridad;

    @NotNull
    private String telefonoFijo;

    @NotNull
    private String celular;

    @NotBlank
    @NotBlank
    private String  email;

}

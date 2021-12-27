package sspc.gob.mx.psr.model;

import lombok.*;
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
@NoArgsConstructor
@AllArgsConstructor
public class Sentenciado extends EntidadBase {
    @Id
    @GeneratedValue
    @Column(name = "id_sentenciado")
    private UUID id;

    @NotNull(message= "sentenced.name.null")
    @NotBlank
    @Size(min = 1, max = 50)
    private String nombre;

    @NotBlank
    @Size(min = 1, max = 50)
    private String apellidoPaterno;

    @NotBlank
    @Size(min = 1, max = 50)
    private String apellidoMaterno;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_cat_pais")
    private Pais nacionalidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_cat_edo_nacimiento")
    private Estado estado;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 18)
    private String documento;

    @OneToOne(mappedBy="sentenciado")
    private Folio folio;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_cat_edo_civil")
    private EstadoCivil estadoCivil;

    @NotBlank
    @Size(min = 1, max = 255)
    private String alias;

    @NotBlank
    @Size(min = 1, max = 255)
    private String otrosNombres;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fch_nacimiento")
    private LocalDate fechaNacimiento;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_cat_ocupacion")
    private Ocupacion ocupacion;

    @Column(name="sexo")
    private Sexo sexo;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_cat_etnia")
    private Etnia etnia;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_cat_escolaridad")
    private Escolaridad escolaridad;

    @NotBlank
    @Column(name = "tel_casa")
    private String telefonoFijo;

    @NotNull
    @NotBlank
    @Column(name = "tel_celular")
    private String celular;

    @NotBlank
    private String correoElectronico;
}

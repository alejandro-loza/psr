package mx.gob.oadprs.sicosel.model;

import com.sun.istack.Nullable;
import lombok.*;
import mx.gob.oadprs.sicosel.enums.Sexo;
import mx.gob.oadprs.sicosel.model.catalog.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "id_sentenciado", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull(message= "sentenced.name.null")
    @NotBlank
    @Size(min = 1, max = 50)
    private String nombre;

    @Size(min = 1, max = 50)
    private String apellidoPaterno;

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

    @ManyToOne
    @JoinColumn(name="fk_id_domicilio")
    @Nullable
    private Domicilio domicilio;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_cat_edo_civil")
    private EstadoCivil estadoCivil;

    @Size(min = 1, max = 255)
    private String alias;

    @Size(min = 1, max = 255)
    private String otrosNombres;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fch_nacimiento")
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name="fk_id_cat_ocupacion")
    private Ocupacion ocupacion;

    @Column(name="sexo")
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name="fk_id_cat_etnia")
    private Etnia etnia;

    @ManyToOne
    @JoinColumn(name="fk_id_cat_escolaridad")
    private Escolaridad escolaridad;

    @Column(name = "tel_casa")
    private String telefonoFijo;

    @NotNull
    @NotBlank
    @Column(name = "tel_celular")
    private String celular;

    private String correoElectronico;
}

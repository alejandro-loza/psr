package sspc.gob.mx.psr.model;

import com.sun.istack.Nullable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
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
public class Presentacion extends EntidadBase {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 10)
    private String folioReporte;

    @NotNull
    @NotBlank
    private UUID idSentenciado;

    @NotNull
    @NotBlank
    @Size(min = 19, max = 19)
    private String folioSentenciado;

    @NotNull
    @NotBlank
    private String numReporte;

    @NotNull
    @NotBlank
    private String totalReporte;

    @NotNull
    @NotBlank
    @Column(name = "periodo_presenta")
    private String periodoPresenta;

    @NotNull
    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fch_impresion")
    private LocalDate fchImpresion;

    @NotNull
    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fch_reporte")
    private LocalDate fchReporte;

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

    @ManyToOne
    @JoinColumn(name="fk_id_domicilio")
    @Nullable
    private Domicilio domicilio;

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

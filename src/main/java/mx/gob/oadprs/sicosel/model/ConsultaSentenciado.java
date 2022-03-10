package mx.gob.oadprs.sicosel.model;

import lombok.*;
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
@Table(name = "vista_consulta")
public class ConsultaSentenciado  {

    @Id
    @Column(name = "id_sentenciado", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull
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

    private String folio;

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

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String nombrePadres;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "appellido_padre")
    private String apellidoPaternoPadres;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "apellido_madre")
    private String apellidoMaternoPadres;

}

package mx.gob.oadprs.sicosel.model;

import lombok.*;
import mx.gob.oadprs.sicosel.model.catalog.*;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaSentenciado  {


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

    private Folio folio;

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
    private String nombreMadre;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String apellidoPaternoMadre;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String apellidoMaternoMadre;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String nombrePadre;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String apellidoPaternoPadre;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String apellidoMaternoPadre;

}

package mx.gob.oadprs.sicosel.model;

import com.sun.istack.Nullable;
import lombok.*;
import mx.gob.oadprs.sicosel.model.catalog.Pais;
import mx.gob.oadprs.sicosel.model.catalog.Parentesco;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Familiar extends EntidadBase {
    @Id
    @GeneratedValue
    @Column(name = "id_familiar", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_sentenciado")
    private Sentenciado sentenciado;

    @ManyToOne
    @JoinColumn(name="fk_id_domicilio")
    @Nullable
    private Domicilio domicilio;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String nombre;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String apellidoPaterno;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String apellidoMaterno;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_cat_pais")
    private Pais nacionalidad;

    @Size(min = 1, max = 18)
    private String documento;

    @Column(name = "tel_casa")
    private String telefonoFijo;

    @Column(name = "tel_celular")
    private String celular;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_cat_parentesco")
    private Parentesco parentesco;
}

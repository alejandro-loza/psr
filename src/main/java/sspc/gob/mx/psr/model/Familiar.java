package sspc.gob.mx.psr.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import sspc.gob.mx.psr.model.catalog.*;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type="uuid-char")
    @Column(name = "id_familiar", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id_sentenciado")
    private Sentenciado sentenciado;

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
    @NotBlank
    @Size(min = 1, max = 18)
    private String documento;

    @NotBlank
    @Column(name = "tel_casa")
    private String telefonoFijo;

    @NotNull
    @NotBlank
    @Column(name = "tel_celular")
    private String celular;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fk_id_cat_parentesco")
    private Parentesco parentesco;
}

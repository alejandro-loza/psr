package mx.gob.oadprs.sicosel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Permiso {

    @Id
    @GeneratedValue
    @Column(name = "id_permiso", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull
    @NotBlank
    @JoinColumn(name="rol")
    private String rol;

    @NotNull
    @NotBlank
    @JoinColumn(name="alcance")
    private String alcance;

    @NotNull
    @NotBlank
    @ManyToOne
    @JoinColumn(name="fk_id_modulo")
    private Modulo modulo;

    @NotNull
    @NotBlank
    @JoinColumn(name="activo")
    private String activo;

}
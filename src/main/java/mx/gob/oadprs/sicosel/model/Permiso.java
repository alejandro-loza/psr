package mx.gob.oadprs.sicosel.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Permiso extends EntidadBase{

    @Id
    @GeneratedValue
    @Column(name = "id_permiso", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull
    @NotBlank
    private String rol;

    @NotNull
    @NotBlank
    private String alcance;

    @ManyToOne
    @JoinColumn(name = "fk_id_modulo", nullable = false, updatable = false )
    private Modulo modulo;

    @NotNull
    @NotBlank
    private String activo;

}
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
public class Modulo {

    @Id
    @GeneratedValue
    @Column(name = "id_modulo", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull
    @NotBlank
    @JoinColumn(name="modulo")
    private String modulo;

    @NotNull
    @NotBlank
    @JoinColumn(name="path_json")
    private String pathJson ;

    @NotNull
    @NotBlank
    @JoinColumn(name="activo")
    private String activo;

}
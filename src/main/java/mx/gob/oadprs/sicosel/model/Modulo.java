package mx.gob.oadprs.sicosel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Modulo  extends EntidadBase{

    @Id
    @GeneratedValue
    @Column(name = "id_modulo", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull
    @NotBlank
    private String modulo;

    @NotNull
    @NotBlank
    private String pathJson;

    @NotNull
    @NotBlank
    private String activo;

}
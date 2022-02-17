package mx.gob.oadprs.sicosel.model.catalog;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Table(name = "cat_edo_civil")
public class EstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_edo_civil")
    public Long id;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 20)
    public String nombre;

    @NotBlank
    @Size(min = 1, max = 50)
    public String descripcion;

    @NotNull
    public boolean activo = true;

}

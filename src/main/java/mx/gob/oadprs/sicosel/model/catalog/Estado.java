package mx.gob.oadprs.sicosel.model.catalog;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Table(name = "cat_estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_estado")
    public Long id;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 20)
    public String nombre;

    @Size(min = 1, max = 50)
    public String descripcion;

    @NotNull
    public boolean activo = true;


}

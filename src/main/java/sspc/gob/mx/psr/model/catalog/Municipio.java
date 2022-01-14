package sspc.gob.mx.psr.model.catalog;


import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Table(name = "cat_municipio")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_municipio")
    public Long id;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 20)
    public String nombre;

    public String descripcion;


    @ManyToOne
    @JoinColumn(name = "fk_id_cat_estado", nullable = false, updatable = false )
    public Estado estado;

    @NotNull
    public boolean activo = true;

}

package mx.gob.oadprs.sicosel.model.catalog;


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

    @NotBlank
    @Size(min = 1, max = 50)
    public String descripcion;


    @ManyToOne
    @JoinColumn(name = "fk_id_cat_estado", nullable = false, updatable = false )
    private Estado estado;

    @NotNull
    public boolean activo = true;

}

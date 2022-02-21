package mx.gob.oadprs.sicosel.model.catalog;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Table(name = "cat_pais")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_pais")
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 30)
    private String nombre;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "iso_alfa_3")
    private String alfa3;

    @Size(min = 1, max = 50)
    private String descripcion;

    @NotNull
    private final boolean activo = true;

}

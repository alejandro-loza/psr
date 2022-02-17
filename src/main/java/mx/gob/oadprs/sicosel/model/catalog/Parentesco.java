package mx.gob.oadprs.sicosel.model.catalog;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Table(name = "cat_parentesco")
public class Parentesco {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_parentesco")
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 20)
    private String nombre;

    @NotBlank
    @Size(min = 1, max = 50)
    private String descripcion;

    @NotNull
    private boolean activo = true;

}

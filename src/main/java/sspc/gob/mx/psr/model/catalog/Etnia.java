package sspc.gob.mx.psr.model.catalog;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "cat_etnia")
public class Etnia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_etnia")
    public Long id;
    public String nombre;
    public String descripcion;
    public boolean activo = true;

}

package sspc.gob.mx.psr.model.catalog;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public
    Long id;
    public String nombre;
    public String alpha2;
    public String alpha3;


}

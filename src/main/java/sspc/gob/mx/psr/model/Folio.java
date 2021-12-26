package sspc.gob.mx.psr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Folio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCodigo;
    private Long nacimientoCodigo;
    private String entidadCodigo;
    private Character sexoCodigo;
    private String nacionalidadCodigo;
    private String consecutivo;
    private Long extra;

    @Override
    public String toString() {
        return  nombreCodigo +
                nacimientoCodigo +
                entidadCodigo +
                sexoCodigo +
                nacionalidadCodigo +
                consecutivo +
                extra;
    }
}
package sspc.gob.mx.psr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Folio {
    @Id
    @GeneratedValue
    @Column(name = "id_folio")
    private UUID id;

    @OneToOne
    @JoinColumn(name="id_sentenciado")
    private Sentenciado sentenciado;

    private String nombreCodigo;
    private Long nacimientoCodigo;
    private String entidadCodigo;
    private Character sexoCodigo;
    private String nacionalidadCodigo;
    private String consecutivo;
    private Long comprobacion;
    private String folio;

    @Override
    public String toString() {
        return  nombreCodigo +
                nacimientoCodigo +
                entidadCodigo +
                sexoCodigo +
                nacionalidadCodigo +
                consecutivo +
                comprobacion;
    }
}
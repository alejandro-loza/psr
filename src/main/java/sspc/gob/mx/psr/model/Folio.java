package sspc.gob.mx.psr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import sspc.gob.mx.psr.enums.Sexo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Folio {
    private static final int COCIENTE_EXTRA = 7;
    @Id
    @GeneratedValue
    private UUID id;

    private String nombreCodigo;
    private int[] nacimientoCodigo;
    private int[] entidadCodigo;
    private char sexoCodigo;
    private String nacionalidadCodigo;
    private int[] consecutivo;
    private int extra;


    public void setNombreCodigo(String nombreCodigo) {
        this.nombreCodigo = StringUtils.stripAccents(nombreCodigo.substring(0, 4).toUpperCase());
    }

    public void setNacimientoCodigo(int[] nacimientoCodigo) {
        this.nacimientoCodigo = Arrays.copyOfRange(nacimientoCodigo, 0, 6);
    }

    public void setEntidadCodigo(int[] entidadCodigo) {
        this.entidadCodigo = Arrays.copyOfRange(entidadCodigo, 0 ,2);
    }

    public void setSexoCodigo(Sexo sexoCodigo) {
        this.sexoCodigo = sexoCodigo.getCodigo();
    }

    public void setNacionalidadCodigo(String nacionalidadCodigo) {
        this.nacionalidadCodigo = StringUtils.stripAccents(nacionalidadCodigo.substring(0, 3).toUpperCase());
    }

    public void setConsecutivo(int[] consecutivo) {
        this.consecutivo =  Arrays.copyOfRange(consecutivo, 0 ,2);
    }

    public void setExtra(){
        this.extra = calculaExtra();
    }

    private int calculaExtra() {
        return Arrays.stream(ArrayUtils.addAll(
                ArrayUtils.addAll(
                        ArrayUtils.addAll(this.nombreCodigo.chars().toArray(), this.nacimientoCodigo),
                        ArrayUtils.addAll(this.entidadCodigo, this.nacionalidadCodigo.chars().toArray())
                ),
                ArrayUtils.addAll(this.consecutivo, (int) this.sexoCodigo)
        )).sum() % COCIENTE_EXTRA;
    }


}
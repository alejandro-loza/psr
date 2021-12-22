package sspc.gob.mx.psr.utils;

import com.google.common.primitives.Ints;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import sspc.gob.mx.psr.enums.Sexo;
import sspc.gob.mx.psr.model.Folio;

import java.nio.LongBuffer;
import java.util.Arrays;

public class FolioBuilder implements IFolioBuilder {
    private static final int COCIENTE_EXTRA = 7;
    private final String nombreCodigo;
    private final int[] nacimientoCodigo;
    private final int[] entidadCodigo;
    private final Sexo sexoCodigo;
    private final String nacionalidadCodigo;
    private final int[] consecutivo;

    public FolioBuilder(String nombreCodigo,
                        int[]  nacimientoCodigo,
                        int[] entidadCodigo,
                        Sexo sexoCodigo,
                        String nacionalidadCodigo,
                        int[] consecutivo) {

        this.nombreCodigo = nombreCodigo;
        this.nacimientoCodigo = nacimientoCodigo;
        this.entidadCodigo = entidadCodigo;
        this.sexoCodigo = sexoCodigo;
        this.nacionalidadCodigo = StringUtils.stripAccents(nacionalidadCodigo.substring(0, 3).toUpperCase());;
        this.consecutivo = consecutivo;
    }

    @Override
    public Folio build() {
        Folio folio = new Folio();
        folio.setNombreCodigo(this.nombreCodigo);
        folio.setNacimientoCodigo(Long.valueOf(Ints.join("", this.nacimientoCodigo)));
        folio.setEntidadCodigo(Long.valueOf(Ints.join("", this.entidadCodigo)));
        folio.setSexoCodigo(this.sexoCodigo.getCodigo());
        folio.setNacionalidadCodigo(this.nacionalidadCodigo);
        folio.setConsecutivo(Ints.join("",this.consecutivo));
        folio.setExtra( calculaExtra());
        return folio;
    }

    private Long calculaExtra() {
        return (long) (Arrays.stream(ArrayUtils.addAll(
                ArrayUtils.addAll(
                        ArrayUtils.addAll(this.nombreCodigo.chars().toArray(), this.nacimientoCodigo),
                        ArrayUtils.addAll(this.entidadCodigo, this.nacionalidadCodigo.chars().toArray())
                ),
                ArrayUtils.addAll(this.consecutivo, (int) this.sexoCodigo.getCodigo())
        )).sum() % COCIENTE_EXTRA);
    }

}

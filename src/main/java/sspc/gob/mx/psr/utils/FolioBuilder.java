package sspc.gob.mx.psr.utils;

import sspc.gob.mx.psr.enums.Sexo;
import sspc.gob.mx.psr.model.Folio;

public class FolioBuilder implements IFolioBuilder {


    private final String nombreCodigo;
    private final int[] nacimientoCodigo;
    private final int[] entidadCodigo;
    private final Sexo sexoCodigo;
    private final String nacionalidadCodigo;
    private final int[] consecutivo;

    public FolioBuilder(String nombreCodigo,
                        int[] nacimientoCodigo,
                        int[] entidadCodigo,
                        Sexo sexoCodigo,
                        String nacionalidadCodigo,
                        int[] consecutivo) {
        this.nombreCodigo = nombreCodigo;
        this.nacimientoCodigo = nacimientoCodigo;
        this.entidadCodigo = entidadCodigo;
        this.sexoCodigo = sexoCodigo;
        this.nacionalidadCodigo = nacionalidadCodigo;
        this.consecutivo = consecutivo;
    }

    @Override
    public Folio build() {
        Folio folio = new Folio();
        folio.setNombreCodigo(this.nombreCodigo);
        folio.setNacimientoCodigo(this.nacimientoCodigo);
        folio.setEntidadCodigo(this.entidadCodigo);
        folio.setSexoCodigo(this.sexoCodigo);
        folio.setNacionalidadCodigo(this.nacionalidadCodigo);
        folio.setConsecutivo(this.consecutivo);
        folio.setExtra();
        return folio;
    }
}

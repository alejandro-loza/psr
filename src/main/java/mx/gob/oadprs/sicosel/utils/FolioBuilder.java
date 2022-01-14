package mx.gob.oadprs.sicosel.utils;

import com.google.common.primitives.Ints;
import mx.gob.oadprs.sicosel.enums.Sexo;
import mx.gob.oadprs.sicosel.model.Folio;
import mx.gob.oadprs.sicosel.model.Sentenciado;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FolioBuilder implements IFolioBuilder {
    private static final int COCIENTE_EXTRA = 7;
    private final Sentenciado sentenciado;

    private final String nombreCodigo;
    private final int[] nacimientoCodigo;
    private final int[] entidadCodigo;
    private final Sexo sexoCodigo;
    private final String nacionalidadCodigo;
    private final int[] consecutivo;

    public FolioBuilder(Sentenciado sentenciado, String nombreCodigo,
                        int[]  nacimientoCodigo,
                        int[] entidadCodigo,
                        String nacionalidadCodigo,
                        int[] consecutivo) {
        this.sentenciado = sentenciado;
        this.nombreCodigo = nombreCodigo;
        this.nacimientoCodigo = nacimientoCodigo;
        this.entidadCodigo = entidadCodigo;
        this.sexoCodigo = sentenciado.getSexo();
        this.nacionalidadCodigo = StringUtils.stripAccents(nacionalidadCodigo.substring(0, 3).toUpperCase());
        this.consecutivo = consecutivo;
    }

    @Override
    public Folio build() {
        Folio folio = new Folio();
        folio.setSentenciado(this.sentenciado);
        folio.setNombreCodigo(this.nombreCodigo);
        folio.setNacimientoCodigo(Long.valueOf(Ints.join("", this.nacimientoCodigo)));
        folio.setEntidadCodigo(Ints.join("", this.entidadCodigo));
        folio.setSexoCodigo(this.sexoCodigo.getCodigo());
        folio.setNacionalidadCodigo(this.nacionalidadCodigo);
        folio.setConsecutivo(Ints.join("",this.consecutivo));
        folio.setComprobacion( calculaExtra());
        folio.setFolio(folio.toString());
        return folio;
    }

    private Long calculaExtra() {
        return (long) ( sumaUnitaria(listaValoresNumericosPorCaracter()) % COCIENTE_EXTRA);
    }

    private List<Integer> listaValoresNumericosPorCaracter() {
        return Arrays.stream(ArrayUtils.addAll(
                ArrayUtils.addAll(
                        ArrayUtils.addAll(this.nombreCodigo.chars().toArray(), this.nacimientoCodigo),
                        ArrayUtils.addAll(this.entidadCodigo, this.nacionalidadCodigo.chars().toArray())
                ),
                ArrayUtils.addAll(this.consecutivo, this.sexoCodigo.getCodigo())
        )).boxed().collect(Collectors.toList());
    }

    private Integer sumaUnitaria(List<Integer> intStream) {
        StringBuilder sb = new StringBuilder();
        for (Integer element: intStream){
            sb.append(element.toString());
        }

        List<Integer> numeros = new ArrayList<>();

        for (int i = 0; i < sb.length(); i++) {
            numeros.add(Character.getNumericValue(sb.charAt(i)));
        }

        return numeros.stream().reduce(0, Integer::sum);
    }

}

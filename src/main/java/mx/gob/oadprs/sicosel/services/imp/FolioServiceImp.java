package mx.gob.oadprs.sicosel.services.imp;

import com.google.common.primitives.Ints;
import mx.gob.oadprs.sicosel.model.Folio;
import mx.gob.oadprs.sicosel.model.Sentenciado;
import mx.gob.oadprs.sicosel.model.catalog.Estado;
import mx.gob.oadprs.sicosel.repository.FolioRepository;
import mx.gob.oadprs.sicosel.services.FolioService;
import mx.gob.oadprs.sicosel.utils.Altisonantes;
import mx.gob.oadprs.sicosel.utils.FolioBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FolioServiceImp implements FolioService {

    public static final String VOCALES = "AaEeIiOoUu";
    public static final String COMODIN_VACIO = "XX";
    public static final char CARACTER_COMODIN = 'X';
    public static final String NACIONALIDAD_MEXICANA = "MEX";

    @Autowired
    public
    FolioRepository folioRepository;

    @Override
    public Folio generar(Sentenciado sentenciado) throws Exception {
        return folioRepository.save(construirFolio(sentenciado));
    }

    @Override
    public Folio construirFolio(Sentenciado sentenciado) throws Exception {
        String codigoPais = sentenciado.getNacionalidad().getAlfa3();
        var codigoEntidad = generarCodigoEntidad(sentenciado.getEstado(), codigoPais);
        String codigoNombre = generarCodigoNombre(sentenciado);
        var codigoFecha = generarCodigoFecha(sentenciado.getFechaNacimiento());
        var consecutivo = getConsecutivo(sentenciado, Ints.join("",codigoEntidad),
                codigoNombre, Long.valueOf(Ints.join("", codigoFecha)));

        return new FolioBuilder(
                sentenciado , codigoNombre, codigoFecha,
                codigoEntidad, codigoPais, consecutivo
        ).build();
    }

    private int[] getConsecutivo(Sentenciado sentenciado,
                                 String codigoEntidad, String codigoNombre, Long codigoFecha) {

        int consecutivo = folioRepository.findAllByParams(codigoNombre, codigoFecha, codigoEntidad,
                sentenciado.getSexo().getCodigo(), sentenciado.getNacionalidad().getAlfa3()).size();
        return consecutivo < 10 ? new int[]{0, consecutivo} : new int[]{consecutivo} ;
    }

    private int[] generarCodigoEntidad(Estado estado, String codigoPais) {
        return !codigoPais.equals(NACIONALIDAD_MEXICANA) ?
                new int[]{99} :  obtenIdEstadoDosCaracteres(estado);
    }

    private int[] obtenIdEstadoDosCaracteres(Estado estado) {
        int estadoId = estado.getId().intValue();
        return estadoId < 10 ? new int[]{0, estadoId} : new int[]{estadoId};
    }

    private int[] generarCodigoFecha(LocalDate fechaNacimiento) {
        return new int[]{Integer.parseInt(
                fechaNacimiento.toString().replace("-", "").substring(2, 8))};
    }

    private String generarCodigoNombre(Sentenciado sentenciado) throws Exception {
        var nombreCodificado = generarCodigoPaterno(sentenciado) +
                generarCodigoMaterno(sentenciado) +
                nombrePrimerCaracter(sentenciado);

        String cambioAltisonante = Altisonantes.listado().get(nombreCodificado.toUpperCase());
        return cambioAltisonante != null ? cambioAltisonante: nombreCodificado;
    }

    private Character nombrePrimerCaracter(Sentenciado sentenciadoInput) throws Exception {
        return Optional.ofNullable(sentenciadoInput)
                .map(Sentenciado::getNombre)
                .map(s -> s.charAt(0))
                .orElseThrow(Exception::new);
    }

    private Character generarCodigoMaterno(Sentenciado sentenciadoInput) {
        return Optional.ofNullable(sentenciadoInput)
                .map(Sentenciado::getApellidoMaterno)
                .map(s -> s.charAt(0)).orElse(CARACTER_COMODIN);
    }

    private String generarCodigoPaterno(Sentenciado sentenciadoInput){
        var apellidoPaterno = StringUtils.stripAccents(Optional.ofNullable(sentenciadoInput)
                .map(Sentenciado::getApellidoPaterno)
                .orElse(COMODIN_VACIO));

        return apellidoPaterno.equals(COMODIN_VACIO)? apellidoPaterno:
                String.valueOf(apellidoPaterno.charAt(0)) +
                        obtenerSegundoCaracter(apellidoPaterno);
    }

    private Character obtenerSegundoCaracter(String  apellido)  {
        if(apellido.length() == 1){
            return CARACTER_COMODIN;
        }
        return buscaPrimerVocal(apellido.toUpperCase());
    }

    private Character buscaPrimerVocal(String apellido) {
        for(var c : apellido.toCharArray()){
            if(VOCALES.indexOf(c) >= 0)
                return c;
        }
        return CARACTER_COMODIN;
    }


}

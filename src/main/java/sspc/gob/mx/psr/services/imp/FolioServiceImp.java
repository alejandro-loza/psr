package sspc.gob.mx.psr.services.imp;

import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.model.Folio;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.repository.FolioRepository;
import sspc.gob.mx.psr.services.FolioService;
import sspc.gob.mx.psr.utils.Altisonantes;
import sspc.gob.mx.psr.utils.FolioBuilder;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

import java.nio.LongBuffer;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FolioServiceImp implements FolioService {

    public static final String VOCALES = "AaEeIiOoUu";
    public static final String COMODIN_VACIO = "XX";
    public static final char CARACTER_COMODIN = 'X';
    public static final String NACIONALIDAD_MEXICANA = "MXN";

    @Autowired
    public
    FolioRepository folioRepository;

    @Override
    public Folio generar(SentenciadoValidador sentenciadoInput, Estado estado, Pais pais) throws Exception {
        return folioRepository.save(construirFolio(sentenciadoInput, estado, pais));
    }

    private Folio construirFolio(SentenciadoValidador sentenciadoInput, Estado estado, Pais pais) throws Exception {
        String codigoPais = pais.getAlpha3();
        var codigoEntidad = generarCodigoEntidad(estado, codigoPais);
        String codigoNombre = generarCodigoNombre(sentenciadoInput);
        var fecha = generarCodigoFecha(sentenciadoInput.getFechaNacimiento());
        var consecutivo = getConsecutivo(sentenciadoInput, pais, Long.valueOf(Ints.join("",codigoEntidad)),
                codigoNombre, Long.valueOf(Ints.join("", fecha)));

        return new FolioBuilder(codigoNombre, fecha, codigoEntidad,
                sentenciadoInput.getSexo(), codigoPais, consecutivo
                ).build();
    }

    private int[] getConsecutivo(SentenciadoValidador sentenciadoInput, Pais pais,
                                 Long codigoEntidad, String codigoNombre, Long fecha) {
        int consecutivo = folioRepository.findAllByParams(codigoNombre, fecha, codigoEntidad,
                sentenciadoInput.getSexo().getCodigo(), pais.getAlpha3()).size() + 1;
        return consecutivo < 10 ? new int[]{0, consecutivo} : new int[]{consecutivo} ;
    }

    private int[] generarCodigoEntidad(Estado estado, String codigoPais) {
        return !codigoPais.equals(NACIONALIDAD_MEXICANA) ?
                new int[]{99} :
                new int[]{estado.getId().intValue()};
    }

    private int[] generarCodigoFecha(LocalDate fechaNacimiento) {
        return new int[]{Integer.parseInt(
                fechaNacimiento.toString().replace("-", "").substring(2, 8))};
    }

    private String generarCodigoNombre(SentenciadoValidador sentenciadoInput) throws Exception {
        var nombreCodificado = generarCodigoPaterno(sentenciadoInput) +
                 generarCodigoMaterno(sentenciadoInput) +
                 nombrePrimerCaracter(sentenciadoInput);

        String cambioAltisonante = Altisonantes.listado().get(nombreCodificado.toUpperCase());
        return cambioAltisonante != null ? cambioAltisonante: nombreCodificado;
    }

    private Character nombrePrimerCaracter(SentenciadoValidador sentenciadoInput) throws Exception {
        return Optional.ofNullable(sentenciadoInput)
                .map(SentenciadoValidador::getNombre)
                .map(s -> s.charAt(0))
                .orElseThrow(Exception::new);
    }

    private Character generarCodigoMaterno(SentenciadoValidador sentenciadoInput) {
        return Optional.ofNullable(sentenciadoInput)
                .map(SentenciadoValidador::getApellidoMaterno)
                .map(s -> s.charAt(0)).orElse(CARACTER_COMODIN);
    }

    private String generarCodigoPaterno(SentenciadoValidador sentenciadoInput){
        var apellidoPaterno = StringUtils.stripAccents(Optional.ofNullable(sentenciadoInput)
                .map(SentenciadoValidador::getApellidoPaterno)
                .orElse(COMODIN_VACIO));

        return apellidoPaterno.equals(COMODIN_VACIO)? apellidoPaterno:
                String.valueOf(apellidoPaterno.charAt(0)) +
                obtenerSegundoCaracter(apellidoPaterno);
    }

    private Character obtenerSegundoCaracter(String  apellido)  {
        if(apellido.length() == 1){
            return CARACTER_COMODIN;
        }
        return buscaPrimerVocal(apellido);
    }

    private Character buscaPrimerVocal(String apellido) {
        for(var c : apellido.toCharArray()){
            if(VOCALES.indexOf(c) >= 0)
              return c;
        }
        return CARACTER_COMODIN;
    }


}

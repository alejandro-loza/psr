package sspc.gob.mx.psr.services.imp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.Folio;
import sspc.gob.mx.psr.repository.EstadoRepository;
import sspc.gob.mx.psr.services.FolioService;
import sspc.gob.mx.psr.utils.Altisonantes;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class FolioServiceImp implements FolioService {

    public static final String VOCALES = "AaEeIiOoUu";
    public static final String COMODIN_VACIO = "XX";
    public static final char CARACTER_COMODIN = 'X';
    public static final String NACIONALIDAD_MEXICANA = "MEXICANA";

    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public Folio generar(SentenciadoValidador sentenciadoInput) throws Exception {
        var estado= buscaClaveEstado(sentenciadoInput);

        String codigoNombre = generarCodigoNombre(sentenciadoInput);
        var fecha = generarCodigoFecha(sentenciadoInput.getFechaNacimiento());

        return null;
    }

    private Long buscaClaveEstado(SentenciadoValidador sentenciadoInput) {
        if(!sentenciadoInput.getNacionalidad().equals(NACIONALIDAD_MEXICANA)){
            return 99L;
        }
         estadoRepository.findById(sentenciadoInput.getEstadoId())
                .orElseThrow(() -> new ItemNotFoundException("estado.notFound") );//TODO pasarlo a properties
        return sentenciadoInput.getEstadoId();

    }

    private int generarCodigoFecha(LocalDate fechaNacimiento) {
        return Integer.parseInt(fechaNacimiento.toString().replace("-","").substring(2, 8));
    }

    private String generarCodigoNombre(SentenciadoValidador sentenciadoInput) throws Exception {
        var nombreCodificado = generarCodigoPaterno(sentenciadoInput) +
                 generarCodigoMaterno(sentenciadoInput) +
                 nombrePrimerCaracter(sentenciadoInput);

        return Optional.of(Altisonantes.listado().get(nombreCodificado))
                                       .orElse(nombreCodificado);
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

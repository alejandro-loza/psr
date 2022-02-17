package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.SentenciadoDto;
import sspc.gob.mx.psr.model.Domicilio;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

import java.util.List;
import java.util.UUID;

public interface SentenciadoService {
    SentenciadoDto crear(SentenciadoValidador sentenciadoValidador) throws Exception;
    Sentenciado busca(UUID id) throws Exception;

    Sentenciado buscaPorFolio(String folio) throws Exception;

    Sentenciado creaDireccion(Sentenciado sentenciado, Domicilio domicilio) throws Exception;

    List<SentenciadoDto> buscaPorNombreCompleto(String nombre, String apellidoPaterno, String apellidoMaterno) throws Exception;

    List<SentenciadoDto> buscaPorNombreApellidoPaterno(String nombre, String apellidoPaterno) throws Exception;
}

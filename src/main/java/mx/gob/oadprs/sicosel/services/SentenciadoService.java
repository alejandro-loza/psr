package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.DomicilioDto;
import mx.gob.oadprs.sicosel.dto.SentenciadoDto;
import mx.gob.oadprs.sicosel.model.Domicilio;
import mx.gob.oadprs.sicosel.model.Sentenciado;
import mx.gob.oadprs.sicosel.validator.DomicilioValidador;
import mx.gob.oadprs.sicosel.validator.SentenciadoValidador;

import java.util.List;
import java.util.UUID;

public interface SentenciadoService {
    SentenciadoDto crear(SentenciadoValidador sentenciadoValidador) throws Exception;
    Sentenciado busca(UUID id) throws Exception;
    DomicilioDto agregaDireccion(UUID sentenciadoId, DomicilioValidador domicilioRequest) throws Exception;
    DomicilioDto buscaDireccion(UUID sentenciadoId) throws Exception;

    Sentenciado buscaPorFolio(String folio) throws Exception;
    Sentenciado creaDireccion(Sentenciado sentenciado, Domicilio domicilio) throws Exception;

    List<SentenciadoDto> buscaPorNombreCompleto(String nombre, String apellidoPaterno, String apellidoMaterno) throws Exception;

    List<SentenciadoDto> buscaPorNombreApellidoPaterno(String nombre, String apellidoPaterno) throws Exception;
}

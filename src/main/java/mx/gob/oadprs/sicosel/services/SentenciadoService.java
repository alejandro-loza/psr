package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.SentenciadoDto;
import mx.gob.oadprs.sicosel.model.Domicilio;
import mx.gob.oadprs.sicosel.model.Sentenciado;
import mx.gob.oadprs.sicosel.validator.SentenciadoValidador;

import java.util.UUID;

public interface SentenciadoService {
    SentenciadoDto crear(SentenciadoValidador sentenciadoValidador) throws Exception;
    Sentenciado busca(UUID id) throws Exception;
    Sentenciado creaDireccion(Sentenciado sentenciado, Domicilio domicilio) throws Exception;
}

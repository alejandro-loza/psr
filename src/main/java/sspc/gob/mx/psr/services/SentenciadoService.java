package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.SentenciadoDto;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

public interface SentenciadoService {
    SentenciadoDto crear(SentenciadoValidador sentenciadoValidador) throws Exception;
}

package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.model.Folio;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

public interface FolioService {
    Folio generar(SentenciadoValidador sentencedValidator, Estado estado, Pais pais) throws  Exception;
    Folio construirFolio(SentenciadoValidador sentenciadoInput, Estado estado, Pais pais) throws Exception;
}

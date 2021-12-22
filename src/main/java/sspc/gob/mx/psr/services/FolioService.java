package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.model.Folio;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

import java.io.IOException;

public interface FolioService {
    Folio generar(SentenciadoValidador sentencedValidator, Estado estado, Pais pais) throws IOException, Exception;
}

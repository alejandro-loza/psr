package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.model.Folio;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

import java.io.IOException;

public interface FolioService {
    Folio generar(SentenciadoValidador sentencedValidator) throws IOException, Exception;
}

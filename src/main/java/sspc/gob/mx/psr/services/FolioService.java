package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.model.Folio;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

public interface FolioService {
    Folio generar(Sentenciado sentenciado) throws  Exception;
    Folio construirFolio(Sentenciado sentenciado) throws Exception;
}

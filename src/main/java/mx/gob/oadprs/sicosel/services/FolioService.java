package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.model.Folio;
import mx.gob.oadprs.sicosel.model.Sentenciado;

public interface FolioService {
    Folio generar(Sentenciado sentenciado) throws  Exception;
    Folio construirFolio(Sentenciado sentenciado) throws Exception;
}

package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.model.Folio;
import sspc.gob.mx.psr.model.Sentenciado;


public interface FolioService {
    Folio generar(Sentenciado sentenciado) throws  Exception;

    Folio buscaPorFolio(String folio) throws  Exception;

    Folio construirFolio(Sentenciado sentenciado) throws Exception;

}

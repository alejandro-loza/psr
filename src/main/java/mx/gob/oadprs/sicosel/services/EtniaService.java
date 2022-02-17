package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.EtniaDto;
import mx.gob.oadprs.sicosel.model.catalog.Etnia;

import java.util.List;

public interface EtniaService {
    List<EtniaDto> lista();

    Etnia busca(Long id);
}

package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.EtniaDto;
import sspc.gob.mx.psr.model.catalog.Etnia;

import java.util.List;

public interface EtniaService {
    List<EtniaDto> lista();

    Etnia busca(Long id);
}

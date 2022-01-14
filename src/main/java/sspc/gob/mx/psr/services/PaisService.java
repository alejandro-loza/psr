package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.PaisDto;
import sspc.gob.mx.psr.exceptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Pais;

import java.util.List;

public interface PaisService {
    Pais busca(Long id) throws ItemNotFoundException;
    List<PaisDto> lista();

}

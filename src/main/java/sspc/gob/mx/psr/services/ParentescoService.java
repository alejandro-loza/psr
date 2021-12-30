package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.ParentescoDto;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Parentesco;

import java.util.List;

public interface ParentescoService {
    Parentesco busca(Long id) throws ItemNotFoundException;
    List<ParentescoDto> lista();
}

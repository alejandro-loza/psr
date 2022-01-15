package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.ParentescoDto;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.catalog.Parentesco;

import java.util.List;

public interface ParentescoService {
    Parentesco busca(Long id) throws ItemNotFoundException;
    List<ParentescoDto> lista();
}

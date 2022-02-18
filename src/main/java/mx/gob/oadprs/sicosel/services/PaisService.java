package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.PaisDto;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.catalog.Pais;

import java.util.List;

public interface PaisService {
    Pais busca(Long id) throws ItemNotFoundException;
    List<PaisDto> lista();

}

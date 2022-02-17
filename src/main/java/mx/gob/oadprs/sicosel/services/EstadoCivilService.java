package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.EstadoCivilDto;
import mx.gob.oadprs.sicosel.model.catalog.EstadoCivil;

import java.util.List;

public interface EstadoCivilService {
    List<EstadoCivilDto> lista();

    EstadoCivil busca(Long id);
}

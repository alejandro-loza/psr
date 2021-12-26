package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.EstadoCivilDto;
import sspc.gob.mx.psr.model.catalog.EstadoCivil;

import java.util.List;

public interface EstadoCivilService {
    List<EstadoCivilDto> lista();

    EstadoCivil busca(Long id);
}

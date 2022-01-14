package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.OcupacionDto;
import mx.gob.oadprs.sicosel.model.catalog.Ocupacion;

import java.util.List;

public interface OcupacionService {
    List<OcupacionDto> lista();

    Ocupacion busca(Long id);
}

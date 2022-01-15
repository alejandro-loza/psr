package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.MunicipioDto;
import mx.gob.oadprs.sicosel.model.catalog.Estado;
import mx.gob.oadprs.sicosel.model.catalog.Municipio;

import java.util.List;

public interface MunicipioService {
    List<MunicipioDto> muncipiosPorEstado(Estado estado);
    Municipio busca(Long id);
}

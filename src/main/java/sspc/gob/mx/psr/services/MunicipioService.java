package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.MunicipioDto;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Municipio;

import java.util.List;

public interface MunicipioService {
    List<MunicipioDto> muncipiosPorEstado(Estado estado);
    Municipio busca(Long id);
}

package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.EstadoDto;
import sspc.gob.mx.psr.model.catalog.Estado;

import java.util.List;

public interface EstadoService {
    List<EstadoDto> lista();
    Estado busca(Long id);

}

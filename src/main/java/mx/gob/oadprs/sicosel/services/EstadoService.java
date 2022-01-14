package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.EstadoDto;
import mx.gob.oadprs.sicosel.model.catalog.Estado;

import java.util.List;

public interface EstadoService {
    List<EstadoDto> lista();
    Estado busca(Long id);

}

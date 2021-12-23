package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.EscolaridadDto;
import sspc.gob.mx.psr.model.catalog.Escolaridad;

import java.util.List;

public interface EscolaridadService {
    List<EscolaridadDto> lista();

    Escolaridad busca(Long id);
}

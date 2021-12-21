package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.model.catalog.Estado;

import java.util.List;

public interface EstadoService {
    List<Estado> lista();
    Estado busca(Long id);

}

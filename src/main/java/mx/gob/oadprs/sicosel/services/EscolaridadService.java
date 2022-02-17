package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.EscolaridadDto;
import mx.gob.oadprs.sicosel.model.catalog.Escolaridad;

import java.util.List;

public interface EscolaridadService {
    List<EscolaridadDto> lista();

    Escolaridad busca(Long id);
}

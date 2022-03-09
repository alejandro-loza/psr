package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.ConsultaSentenciadoDto;
import mx.gob.oadprs.sicosel.utils.SentenciadoCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConsultaSentenciadoService {
    List<ConsultaSentenciadoDto> consulta(SentenciadoCriteria criteriosBusqueda, Pageable pageable);
}

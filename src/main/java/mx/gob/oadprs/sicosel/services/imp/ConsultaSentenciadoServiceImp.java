package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.ConsultaSentenciadoDto;
import mx.gob.oadprs.sicosel.model.ConsultaSentenciado;
import mx.gob.oadprs.sicosel.model.filter.ConsultaSentenciadoSpecifications;
import mx.gob.oadprs.sicosel.repository.ConsultaSentenciadoRepository;
import mx.gob.oadprs.sicosel.services.ConsultaSentenciadoService;
import mx.gob.oadprs.sicosel.utils.SentenciadoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaSentenciadoServiceImp implements ConsultaSentenciadoService {
    @Autowired
    ConsultaSentenciadoRepository repository;

    @Override
    public List<ConsultaSentenciadoDto> consulta(SentenciadoCriteria criteriosBusqueda, Pageable pageable) {

        Specification<ConsultaSentenciado> especificaciones = ConsultaSentenciadoSpecifications
                .creaSentenciadoSpecifications(criteriosBusqueda);

        return this.repository.findAll(especificaciones, pageable).toList()
                 .stream().map(ConsultaSentenciadoDto::new).collect(Collectors.toList());
    }
}

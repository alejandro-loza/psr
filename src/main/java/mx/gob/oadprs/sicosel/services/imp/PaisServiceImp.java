package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.gob.oadprs.sicosel.dto.PaisDto;
import mx.gob.oadprs.sicosel.exeptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.catalog.Pais;
import mx.gob.oadprs.sicosel.repository.catalog.PaisRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaisServiceImp implements PaisService {
    @Autowired
    PaisRepository paisRepository;

    @Override
    public Pais busca(Long id) {
        return paisRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("pais.notFound") );
    }

    @Override
    public List<PaisDto> lista() {
        return paisRepository.findAll()
                .stream().map(PaisDto::new).collect(Collectors.toList());
    }
}

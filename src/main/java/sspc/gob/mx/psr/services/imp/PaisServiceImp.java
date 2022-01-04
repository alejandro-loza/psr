package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.PaisDto;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.repository.catalog.PaisRepository;
import sspc.gob.mx.psr.services.PaisService;

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

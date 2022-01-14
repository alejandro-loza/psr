package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.ParentescoDto;
import sspc.gob.mx.psr.exceptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Parentesco;
import sspc.gob.mx.psr.repository.catalog.ParentescoRepository;
import sspc.gob.mx.psr.services.ParentescoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentescoServiceImp implements ParentescoService {

    @Autowired
    ParentescoRepository parentescoRepository;

    @Override
    public Parentesco busca(Long id) throws ItemNotFoundException {
        return parentescoRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("parentesco.notFound") );
    }

    @Override
    public List<ParentescoDto> lista() {
        return parentescoRepository.findAll()
                .stream().map(ParentescoDto::new).collect(Collectors.toList());

    }
}

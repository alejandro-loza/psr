package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.model.catalog.Parentesco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.gob.oadprs.sicosel.dto.ParentescoDto;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.repository.catalog.ParentescoRepository;
import mx.gob.oadprs.sicosel.services.ParentescoService;

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

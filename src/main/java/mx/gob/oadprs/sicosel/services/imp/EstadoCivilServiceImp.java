package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.services.EstadoCivilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.gob.oadprs.sicosel.dto.EstadoCivilDto;
import mx.gob.oadprs.sicosel.exeptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.catalog.EstadoCivil;
import mx.gob.oadprs.sicosel.repository.catalog.EstadoCivilRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoCivilServiceImp implements EstadoCivilService {

    @Autowired
    EstadoCivilRepository estadoCivilRepository;

    @Override
    public List<EstadoCivilDto> lista(){
        return estadoCivilRepository.findAll()
                .stream().map(EstadoCivilDto::new).collect(Collectors.toList());
   }

    @Override
    public EstadoCivil busca(Long id) {
        return  estadoCivilRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("estadoCivil.notFound") );
    }
}

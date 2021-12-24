package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.EstadoCivilDto;
import sspc.gob.mx.psr.dto.EstadoDto;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.EstadoCivil;
import sspc.gob.mx.psr.repository.EstadoCivilRepository;
import sspc.gob.mx.psr.services.EstadoCivilService;
import sspc.gob.mx.psr.services.EstadoService;

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

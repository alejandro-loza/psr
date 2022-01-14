package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.EstadoDto;
import sspc.gob.mx.psr.exceptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.repository.catalog.EstadoRepository;
import sspc.gob.mx.psr.services.EstadoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoServiceImp implements EstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public List<EstadoDto> lista(){
        return estadoRepository.findAll()
                .stream().map(EstadoDto::new).collect(Collectors.toList());
   }

    @Override
    public Estado busca(Long id) {
        return  estadoRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("estado.notFound") );
    }
}

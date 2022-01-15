package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.EstadoDto;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.catalog.Estado;
import mx.gob.oadprs.sicosel.repository.catalog.EstadoRepository;
import mx.gob.oadprs.sicosel.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

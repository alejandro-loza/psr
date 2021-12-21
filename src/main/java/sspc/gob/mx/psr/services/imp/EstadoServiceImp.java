package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.repository.EstadoRepository;
import sspc.gob.mx.psr.services.EstadoService;

import java.util.List;

@Service
public class EstadoServiceImp implements EstadoService {

   @Autowired
    EstadoRepository estadoRepository;

    @Override
    public List<Estado> lista(){
       return estadoRepository.findAll();

   }

    @Override
    public Estado busca(Long id) {
        return  estadoRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("estado.notFound") );
    }
}

package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.repository.PaisRepository;
import sspc.gob.mx.psr.services.PaisService;

@Service
public class PaisServiceImp implements PaisService {
    @Autowired
    PaisRepository paisRepository;

    @Override
    public Pais busca(Long id) {
        return paisRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("pais.notFound") );
    }
}

package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.OcupacionDto;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Ocupacion;
import sspc.gob.mx.psr.repository.catalog.OcupacionRepository;
import sspc.gob.mx.psr.services.OcupacionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OcupacionServiceImp implements OcupacionService {

    @Autowired
    OcupacionRepository ocupacionRepository;

    @Override
    public List<OcupacionDto> lista(){
        return ocupacionRepository.findAll()
                .stream().map(OcupacionDto::new).collect(Collectors.toList());
   }

    @Override
    public Ocupacion busca(Long id) {
        return  ocupacionRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("ocupacion.notFound") );
    }
}

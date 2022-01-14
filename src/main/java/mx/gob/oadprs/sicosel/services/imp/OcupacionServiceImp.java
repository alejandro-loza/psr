package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.OcupacionDto;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.catalog.Ocupacion;
import mx.gob.oadprs.sicosel.services.OcupacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.gob.oadprs.sicosel.repository.catalog.OcupacionRepository;

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

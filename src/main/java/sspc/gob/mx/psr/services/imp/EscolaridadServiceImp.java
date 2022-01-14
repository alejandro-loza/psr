package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.EscolaridadDto;
import sspc.gob.mx.psr.exceptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Escolaridad;
import sspc.gob.mx.psr.repository.catalog.EscolaridadRepository;
import sspc.gob.mx.psr.services.EscolaridadService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EscolaridadServiceImp implements EscolaridadService {

    @Autowired
    EscolaridadRepository escolaridadRepository;

    @Override
    public List<EscolaridadDto> lista(){
        return escolaridadRepository.findAll()
                .stream().map(EscolaridadDto::new).collect(Collectors.toList());
   }

    @Override
    public Escolaridad busca(Long id) throws ItemNotFoundException {
        return  escolaridadRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("escolaridad.notFound") );
    }
}

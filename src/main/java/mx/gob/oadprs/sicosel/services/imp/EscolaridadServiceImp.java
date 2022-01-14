package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.EscolaridadDto;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.catalog.Escolaridad;
import mx.gob.oadprs.sicosel.services.EscolaridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.gob.oadprs.sicosel.repository.catalog.EscolaridadRepository;

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

package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.EtniaDto;
import mx.gob.oadprs.sicosel.model.catalog.Etnia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.repository.catalog.EtniaRepository;
import mx.gob.oadprs.sicosel.services.EtniaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtniaServiceImp implements EtniaService {

    @Autowired
    EtniaRepository etniaRepository;

    @Override
    public List<EtniaDto> lista(){
        return etniaRepository.findAll()
                .stream().map(EtniaDto::new).collect(Collectors.toList());
    }

    @Override
    public Etnia busca(Long id) {
        return  etniaRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("etnia.notFound") );
    }
}

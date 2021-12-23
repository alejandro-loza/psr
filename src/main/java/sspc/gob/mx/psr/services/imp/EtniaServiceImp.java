package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.EstadoDto;
import sspc.gob.mx.psr.dto.EtniaDto;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Etnia;
import sspc.gob.mx.psr.repository.EtniaRepository;
import sspc.gob.mx.psr.services.EtniaService;

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

package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.MunicipioDto;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Municipio;
import sspc.gob.mx.psr.repository.catalog.MunicipioRepository;
import sspc.gob.mx.psr.services.MunicipioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioServiceImp implements MunicipioService {
    @Autowired
    MunicipioRepository municipioRepository;

    @Override
    public List<MunicipioDto> muncipiosPorEstado(Estado estado) {
        return municipioRepository.findAllByEstado(estado)
                .stream().map(MunicipioDto::new).collect(Collectors.toList());
    }

    @Override
    public Municipio busca(Long id) {
        return  municipioRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("municipio.notFound") );
    }
}

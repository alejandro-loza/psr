package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.MunicipioDto;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.catalog.Estado;
import mx.gob.oadprs.sicosel.model.catalog.Municipio;
import mx.gob.oadprs.sicosel.repository.catalog.MunicipioRepository;
import mx.gob.oadprs.sicosel.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioServiceImp implements MunicipioService {
    @Autowired
    MunicipioRepository municipioRepository;

    @Override
    public List<MunicipioDto> muncipiosPorEstado(Estado estado) {
        return municipioRepository.findAllByEstadoAndActivo(estado, true)
                .stream().map(MunicipioDto::new).collect(Collectors.toList());
    }

    @Override
    public Municipio busca(Long id) {
        return  municipioRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("municipio.notFound") );
    }
}

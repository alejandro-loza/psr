package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.MunicipioDto;
import mx.gob.oadprs.sicosel.dto.PermisoDto;
import mx.gob.oadprs.sicosel.model.Permiso;
import mx.gob.oadprs.sicosel.repository.PermisoRepository;
import mx.gob.oadprs.sicosel.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PermisoServiceImp implements PermisoService {


    @Autowired
    PermisoRepository permisoRepository;

    @Override
    public List<PermisoDto> getPermisos(String rol)  {
        return permisoRepository.findAllByRol(rol)
           .stream().map(PermisoDto::new).collect(Collectors.toList());

    }
}


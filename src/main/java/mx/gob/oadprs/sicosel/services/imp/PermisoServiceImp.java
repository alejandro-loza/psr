package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.PermisoDto;
import mx.gob.oadprs.sicosel.repository.PermisoRepository;
import mx.gob.oadprs.sicosel.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PermisoServiceImp implements PermisoService {


    @Autowired
    PermisoRepository permisoRepository;

    @Override
    public List<PermisoDto> getPermisos(String rol)  {
            List<PermisoDto> permisos = permisoRepository.findAllByRolAndActivo(rol, true);
            return permisos;
        }
    }


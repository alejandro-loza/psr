package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.PermisoDto;
import mx.gob.oadprs.sicosel.repository.PermisoRepository;
import mx.gob.oadprs.sicosel.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PermisoServiceImp implements PermisoService {

    String rol = "ROL_ADMON";

    @Autowired
    PermisoRepository permisoRepository;

    @Override
    public List getPermisos(String Rol) throws Exception {
        try {
            List<PermisoDto> permisos;
            permisos = permisoRepository.findByRol(rol);
            System.out.println("Entro " + permisos.toString());
            return permisos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

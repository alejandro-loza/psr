package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.PermisoDto;
import mx.gob.oadprs.sicosel.model.Permiso;

import java.util.List;

public interface PermisoService {

    List<PermisoDto> getPermisos(String rol);
}

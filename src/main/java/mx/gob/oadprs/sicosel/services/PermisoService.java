package mx.gob.oadprs.sicosel.services;

import java.util.List;

public interface PermisoService {

    List getPermisos(String rol) throws Exception;
}

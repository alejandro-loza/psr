package mx.gob.oadprs.sicosel.repository;

import mx.gob.oadprs.sicosel.model.Permiso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisoRepository extends CrudRepository<Permiso, Long> {

    List<Permiso> findAllByRol(String rol);

}




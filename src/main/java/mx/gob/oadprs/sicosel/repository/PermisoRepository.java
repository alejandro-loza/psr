package mx.gob.oadprs.sicosel.repository;

import mx.gob.oadprs.sicosel.dto.PermisoDto;
import mx.gob.oadprs.sicosel.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermisoRepository extends CrudRepository<Permiso, Long> {

    List<PermisoDto>findAllByRolAndActivo (String rol, boolean activo);


    }




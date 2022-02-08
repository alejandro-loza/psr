package mx.gob.oadprs.sicosel.repository;

import mx.gob.oadprs.sicosel.dto.PermisoDto;
import mx.gob.oadprs.sicosel.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {

    @Query(
            value = "select rol, alcance, modulo, path_json " +
                    " from seg.permiso p " +
                    " inner join seg.modulo m on p.fk_id_modulo = m.id_modulo " +
                    " where p.rol = :rol " +
                    " and p.activo = '1' " +
                    " and m.activo = '1'",
            nativeQuery = true
    )

    List<PermisoDto>findByRol (@Param("rol")String rol);

    }




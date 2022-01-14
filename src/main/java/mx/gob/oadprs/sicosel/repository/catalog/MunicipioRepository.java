package mx.gob.oadprs.sicosel.repository.catalog;

import mx.gob.oadprs.sicosel.model.catalog.Estado;
import mx.gob.oadprs.sicosel.model.catalog.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository  extends JpaRepository<Municipio, Long> {
    List<Municipio> findAllByEstado(Estado estado);
    List<Municipio> findAllByEstadoAndActivo(Estado estado, boolean activo);
}

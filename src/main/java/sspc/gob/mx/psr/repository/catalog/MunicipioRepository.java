package sspc.gob.mx.psr.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Municipio;

import java.util.List;

@Repository
public interface MunicipioRepository  extends JpaRepository<Municipio, Long> {
    List<Municipio> findAllByEstado(Estado estado);
}

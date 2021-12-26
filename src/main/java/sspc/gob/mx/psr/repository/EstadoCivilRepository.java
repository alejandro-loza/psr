package sspc.gob.mx.psr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.EstadoCivil;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Long> {
}


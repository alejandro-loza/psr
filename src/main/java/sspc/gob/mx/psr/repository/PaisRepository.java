package sspc.gob.mx.psr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sspc.gob.mx.psr.model.catalog.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
}

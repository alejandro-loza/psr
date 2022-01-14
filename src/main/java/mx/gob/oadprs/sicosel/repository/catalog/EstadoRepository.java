package mx.gob.oadprs.sicosel.repository.catalog;

import mx.gob.oadprs.sicosel.model.catalog.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}


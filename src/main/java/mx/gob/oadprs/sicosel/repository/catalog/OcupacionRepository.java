package mx.gob.oadprs.sicosel.repository.catalog;

import mx.gob.oadprs.sicosel.model.catalog.Ocupacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcupacionRepository extends JpaRepository<Ocupacion, Long> {
}


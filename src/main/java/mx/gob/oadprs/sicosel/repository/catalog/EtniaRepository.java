package mx.gob.oadprs.sicosel.repository.catalog;

import mx.gob.oadprs.sicosel.model.catalog.Etnia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtniaRepository extends JpaRepository<Etnia, Long> {
}

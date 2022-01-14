package mx.gob.oadprs.sicosel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.gob.oadprs.sicosel.model.Domicilio;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}

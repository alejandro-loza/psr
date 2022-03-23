package mx.gob.oadprs.sicosel.repository;

import mx.gob.oadprs.sicosel.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
    Optional<Domicilio> findById(UUID id);
}

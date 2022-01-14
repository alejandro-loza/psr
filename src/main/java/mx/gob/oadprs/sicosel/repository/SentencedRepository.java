package mx.gob.oadprs.sicosel.repository;

import mx.gob.oadprs.sicosel.model.Sentenciado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("SentencedRepository")
public interface SentencedRepository extends JpaRepository<Sentenciado, UUID> {
    Optional<Sentenciado> findById(UUID id);
}

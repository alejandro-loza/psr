package sspc.gob.mx.psr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sspc.gob.mx.psr.model.Familiar;
import sspc.gob.mx.psr.model.Sentenciado;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FamiliarRepository  extends JpaRepository<Familiar, UUID> {
    Optional<Familiar> findById(UUID id);
    List<Familiar> findAllBySentenciado(Sentenciado sentenciado);
}

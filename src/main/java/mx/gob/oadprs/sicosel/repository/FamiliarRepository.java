package mx.gob.oadprs.sicosel.repository;

import mx.gob.oadprs.sicosel.model.Familiar;
import mx.gob.oadprs.sicosel.model.Sentenciado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FamiliarRepository  extends JpaRepository<Familiar, UUID> {
    Optional<Familiar> findById(UUID id);
    List<Familiar> findAllBySentenciado(Sentenciado sentenciado);
}

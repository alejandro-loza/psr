package sspc.gob.mx.psr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sspc.gob.mx.psr.model.Sentenciado;

import java.util.Optional;
import java.util.UUID;

@Repository("SentencedRepository")
public interface SentencedRepository extends JpaRepository<Sentenciado, UUID> {
    Optional<Sentenciado> findById(UUID id);
    Optional<Sentenciado> findByNombreAndApellidoPaternoAndApellidoMaterno(
            String nombre, String apellidoPaterno, String apellidoMaterno);
}

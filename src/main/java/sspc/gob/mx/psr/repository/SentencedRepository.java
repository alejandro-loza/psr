package sspc.gob.mx.psr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sspc.gob.mx.psr.model.Sentenciado;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("SentencedRepository")
public interface SentencedRepository extends JpaRepository<Sentenciado, UUID> {
    Optional<Sentenciado> findById(UUID id);
    List<Sentenciado> findAllByNombreAndApellidoPaternoAndApellidoMaterno(
            String nombre, String apellidoPaterno, String apellidoMaterno);
    Optional<Sentenciado> findAllByNombreAndApellidoPaterno(
            String nombre, String apellidoPaterno);
}

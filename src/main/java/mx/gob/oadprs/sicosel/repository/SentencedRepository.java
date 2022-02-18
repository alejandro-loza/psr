package mx.gob.oadprs.sicosel.repository;

import mx.gob.oadprs.sicosel.model.Sentenciado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("SentencedRepository")
public interface SentencedRepository extends JpaRepository<Sentenciado, UUID> {
    Optional<Sentenciado> findById(UUID id);
    List<Sentenciado> findAllByNombreAndApellidoPaternoAndApellidoMaterno(
            String nombre, String apellidoPaterno, String apellidoMaterno);
    List<Sentenciado> findAllByNombreAndApellidoPaterno(
            String nombre, String apellidoPaterno);
}

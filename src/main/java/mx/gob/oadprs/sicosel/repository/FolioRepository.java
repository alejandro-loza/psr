package mx.gob.oadprs.sicosel.repository;

import mx.gob.oadprs.sicosel.model.Folio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolioRepository extends JpaRepository<Folio, Long> {

    @Query("SELECT f FROM Folio f WHERE" +
            " f.nombreCodigo = ?1 and" +
            " f.nacimientoCodigo = ?2 and" +
            " f.entidadCodigo = ?3 and" +
            " f.sexoCodigo = ?4 and" +
            " f.nacionalidadCodigo = ?5")
    List<Folio> findAllByParams(
            String nombreCodigo,
            Long nacimientoCodigo,
            String entidadCodigo,
            Character sexoCodigo,
            String nacionalidadCodigo);

    Optional<Folio> findByFolio(String folio);
}

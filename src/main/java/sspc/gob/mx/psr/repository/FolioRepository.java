package sspc.gob.mx.psr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sspc.gob.mx.psr.model.Folio;

import java.util.List;

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
}

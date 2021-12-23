package sspc.gob.mx.psr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sspc.gob.mx.psr.dto.EscolaridadDto;
import sspc.gob.mx.psr.model.catalog.Escolaridad;
import sspc.gob.mx.psr.model.catalog.Estado;

import java.util.List;

@Repository
public interface EscolaridadRepository extends JpaRepository<Escolaridad, Long> {
}


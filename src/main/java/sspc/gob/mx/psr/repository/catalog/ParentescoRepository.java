package sspc.gob.mx.psr.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.model.catalog.Parentesco;

@Repository
public interface ParentescoRepository  extends JpaRepository<Parentesco, Long> {
}

package sspc.gob.mx.psr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sspc.gob.mx.psr.model.Sentenced;

@Repository
public interface SentencedRepository extends JpaRepository<Sentenced, Long> {
}

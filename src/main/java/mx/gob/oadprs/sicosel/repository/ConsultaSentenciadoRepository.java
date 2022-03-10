package mx.gob.oadprs.sicosel.repository;

import mx.gob.oadprs.sicosel.model.ConsultaSentenciado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;


@Repository
public interface ConsultaSentenciadoRepository extends JpaRepository<ConsultaSentenciado, Integer>,
        JpaSpecificationExecutor<ConsultaSentenciado> {

    Page<ConsultaSentenciado> findAll(@Nullable Specification<ConsultaSentenciado> spec, Pageable pageable);
}

package sspc.gob.mx.psr.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class EntidadBase {

    @Column(updatable = false)
    @CreationTimestamp
    protected LocalDateTime dateCreated;

    @UpdateTimestamp
    protected LocalDateTime lastUpdated;

    @Column(updatable = false)
    @CreationTimestamp
    protected LocalDateTime dateDeleted;
}
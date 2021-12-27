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

    @Column(updatable = false, name = "aud_fch_alta")
    @CreationTimestamp
    protected LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(updatable = false, name = "aud_fch_modificacion")
    protected LocalDateTime ultimaModificacion;

    @CreationTimestamp
    @Column(updatable = false, name = "aud_fch_fecha_eliminacion")
    protected LocalDateTime fechaEliminacion;
}
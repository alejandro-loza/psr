package sspc.gob.mx.psr.dto;

import lombok.Data;
import sspc.gob.mx.psr.model.catalog.Estado;

@Data
public class EstadoDto {
    public Long id;
    public String nombre;

    public EstadoDto(Estado estado) {
        super();
        this.id = estado.id;
        this.nombre = estado.nombre;
    }

}

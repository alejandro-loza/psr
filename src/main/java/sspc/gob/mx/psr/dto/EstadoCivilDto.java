package sspc.gob.mx.psr.dto;

import lombok.Data;
import sspc.gob.mx.psr.model.catalog.EstadoCivil;

@Data
public class EstadoCivilDto {

    public Long id;
    public String nombre;

    public EstadoCivilDto(EstadoCivil estado) {
        super();
        this.id = estado.id;
        this.nombre = estado.nombre;
    }
}

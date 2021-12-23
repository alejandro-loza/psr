package sspc.gob.mx.psr.dto;

import lombok.Data;
import sspc.gob.mx.psr.model.catalog.Escolaridad;

@Data
public class EscolaridadDto {
    public Long id;
    public String nombre;

    public EscolaridadDto(Escolaridad escolaridad) {
        super();
        this.id = escolaridad.id;
        this.nombre = escolaridad.nombre;
    }
}

package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.catalog.Estado;

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

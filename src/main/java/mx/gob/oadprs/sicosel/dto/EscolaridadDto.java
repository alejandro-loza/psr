package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.catalog.Escolaridad;

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

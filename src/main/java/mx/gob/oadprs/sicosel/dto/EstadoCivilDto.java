package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.catalog.EstadoCivil;

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

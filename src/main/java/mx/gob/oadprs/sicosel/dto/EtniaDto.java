package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.catalog.Etnia;

@Data
public class EtniaDto {

    public Long id;
    public String nombre;

    public EtniaDto(Etnia etnia) {
        super();
        this.id = etnia.id;
        this.nombre = etnia.nombre;
    }

}

package sspc.gob.mx.psr.dto;

import lombok.Data;
import sspc.gob.mx.psr.model.catalog.Etnia;

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

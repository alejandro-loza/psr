package sspc.gob.mx.psr.dto;

import lombok.Data;
import sspc.gob.mx.psr.model.catalog.Pais;

@Data
public class PaisDto {
    public Long id;
    public String nombre;
    public String alpha2;
    public String alpha3;

    public PaisDto(Pais pais) {
        this.id = pais.id;
        this.nombre = pais.nombre;
        this.alpha2 = pais.alpha2;
        this.alpha3 = pais.alpha3;
    }

}

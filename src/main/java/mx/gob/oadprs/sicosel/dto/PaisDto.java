package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.catalog.Pais;

@Data
public class PaisDto {
    public Long id;
    public String nombre;
    public String alfa3;

    public PaisDto(Pais pais) {
        this.id = pais.getId();
        this.nombre = pais.getNombre();
        this.alfa3 = pais.getAlfa3();
    }

}

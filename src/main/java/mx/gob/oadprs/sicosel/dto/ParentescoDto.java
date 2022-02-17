package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.catalog.Parentesco;

@Data
public class ParentescoDto {
    public Long id;
    public String nombre;

    public ParentescoDto(Parentesco parentesco) {
        super();
        this.id = parentesco.getId();
        this.nombre = parentesco.getNombre();
    }
}

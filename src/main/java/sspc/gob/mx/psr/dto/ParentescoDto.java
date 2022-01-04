package sspc.gob.mx.psr.dto;

import lombok.Data;
import sspc.gob.mx.psr.model.catalog.Parentesco;

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

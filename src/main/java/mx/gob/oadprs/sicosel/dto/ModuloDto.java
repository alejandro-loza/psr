package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.Modulo;
import mx.gob.oadprs.sicosel.model.Permiso;

@Data
public class ModuloDto {

    String id;
    String modulo;
    String pathJson;
    String activo;

    public ModuloDto(Modulo modulo) {
        super();
        this.id = modulo.getId().toString();
        this.modulo = modulo.getModulo();
        this.pathJson = modulo.getPathJson();
        this.activo = modulo.getActivo();
    }

}

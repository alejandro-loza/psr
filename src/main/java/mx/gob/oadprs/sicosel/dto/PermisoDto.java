package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.Modulo;
import mx.gob.oadprs.sicosel.model.Permiso;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PermisoDto {

    String rol;
    String alcance;
    String modulo;
    String path;

    public PermisoDto(PermisoDto permiso) {
        super();
        this.rol = permiso.getRol();
        this.alcance = permiso.getAlcance();
        this.modulo = permiso.getModulo();
        this.rol = permiso.getPath();
    }

}

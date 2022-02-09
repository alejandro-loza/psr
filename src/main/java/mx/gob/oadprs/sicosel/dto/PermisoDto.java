package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.Permiso;

@Data
public class PermisoDto {

    String rol;
    String alcance;
    String modulo;
    String path;

    public PermisoDto(Permiso permiso) {
        super();
        this.rol = permiso.getRol();
        this.alcance = permiso.getAlcance();
        this.modulo = permiso.getModulo().getModulo();
        this.rol = permiso.getRol();
    }

}

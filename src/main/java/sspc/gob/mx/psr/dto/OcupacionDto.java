package sspc.gob.mx.psr.dto;

import sspc.gob.mx.psr.model.catalog.Ocupacion;

public class OcupacionDto {
    public Long id;
    public String nombre;

    public OcupacionDto(Ocupacion ocupacion) {
        super();
        this.id = ocupacion.id;
        this.nombre = ocupacion.nombre;
    }
}

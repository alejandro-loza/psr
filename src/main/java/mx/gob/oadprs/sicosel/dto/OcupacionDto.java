package mx.gob.oadprs.sicosel.dto;

import mx.gob.oadprs.sicosel.model.catalog.Ocupacion;

public class OcupacionDto {
    public Long id;
    public String nombre;

    public OcupacionDto(Ocupacion ocupacion) {
        super();
        this.id = ocupacion.id;
        this.nombre = ocupacion.nombre;
    }
}

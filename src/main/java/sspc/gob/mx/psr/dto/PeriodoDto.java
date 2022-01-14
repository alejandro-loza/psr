package sspc.gob.mx.psr.dto;

import lombok.Data;
import sspc.gob.mx.psr.model.catalog.Etnia;
import sspc.gob.mx.psr.model.catalog.Periodo;

@Data
public class PeriodoDto {

    public Long id;
    public String nombre;

    public PeriodoDto(Periodo periodo) {
        super();
        this.id = periodo.id;
        this.nombre = periodo.nombre;
    }

}

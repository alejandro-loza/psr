package mx.gob.oadprs.sicosel.dto;

import lombok.Getter;
import lombok.Setter;
import mx.gob.oadprs.sicosel.model.ConsultaSentenciado;
import mx.gob.oadprs.sicosel.model.Folio;
import mx.gob.oadprs.sicosel.model.Sentenciado;

import java.time.LocalDate;


@Getter
@Setter
public class ConsultaSentenciadoDto {

    String id;

    String folio;

    String nombre;

    String apellidoPaterno;

    String apellidoMaterno;

    String procesos;


    public ConsultaSentenciadoDto(ConsultaSentenciado sentenciado){
        this.id = sentenciado.getId().toString();
        this.folio = sentenciado.getFolio();
        this.nombre = sentenciado.getNombre();
        this.apellidoPaterno = sentenciado.getApellidoPaterno();
        this.apellidoMaterno = sentenciado.getApellidoMaterno();
    }


}

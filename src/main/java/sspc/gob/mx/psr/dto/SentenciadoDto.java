package sspc.gob.mx.psr.dto;

import lombok.Getter;
import lombok.Setter;
import sspc.gob.mx.psr.model.Sentenciado;

import javax.validation.constraints.*;
import java.time.LocalDateTime;


@Getter
@Setter
public class SentenciadoDto {

    String id;

    String nombre;

    String apellidoPaterno;

    String apellidoMaterno;

    String nacionalidad;

    String curp;

    String estadoCivil;

    String alias;

    String otrosNombres;

    Long fechaNacimiento;

    String ocupacion;

    String sexo;

    String etnia;

    String escolaridad;

    String telefonoFijo;

    String celular;

    String  email;

    LocalDateTime dateCreated;

    public SentenciadoDto(Sentenciado sentenciado){
        this.id = sentenciado.getId().toString();
        this.nombre = sentenciado.getNombre();
        this.apellidoPaterno = sentenciado.getApellidoPaterno();
        this.apellidoMaterno = sentenciado.getApellidoMaterno();
        this.nacionalidad = sentenciado.getNacionalidad();
        this.curp = sentenciado.getCurp();
        this.estadoCivil = sentenciado.getEstadoCivil();
        this.alias = sentenciado.getAlias();
        this.otrosNombres = sentenciado.getOtrosNombres();
        this.fechaNacimiento = sentenciado.getFechaNacimiento().getTime();
        this.ocupacion = sentenciado.getOcupacion();
        this.sexo = sentenciado.getSexo();
        this.etnia = sentenciado.getEtnia();
        this.escolaridad = sentenciado.getEscolaridad();
        this.telefonoFijo = sentenciado.getTelefonoFijo();
        this.celular = sentenciado.getCelular();
        this.email = sentenciado.getEmail();
        this.dateCreated = sentenciado.getDateCreated();
    }

}

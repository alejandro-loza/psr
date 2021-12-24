package sspc.gob.mx.psr.dto;

import lombok.Getter;
import lombok.Setter;
import sspc.gob.mx.psr.model.Sentenciado;

import java.time.LocalDate;


@Getter
@Setter
public class SentenciadoDto {

    String id;

    String folio;

    String nombre;

    String apellidoPaterno;

    String apellidoMaterno;

    String nacionalidad;

    String estado;

    String curp;

    String estadoCivil;

    String alias;

    String otrosNombres;

    LocalDate fechaNacimiento;

    String ocupacion;

    String sexo;

    String etnia;

    String escolaridad;

    String telefonoFijo;

    String celular;

    String  email;


    public SentenciadoDto(Sentenciado sentenciado){
        this.id = sentenciado.getId().toString();
        this.folio = sentenciado.getFolio().toString();
        this.nombre = sentenciado.getNombre();
        this.apellidoPaterno = sentenciado.getApellidoPaterno();
        this.apellidoMaterno = sentenciado.getApellidoMaterno();
        this.nacionalidad = sentenciado.getNacionalidad().getNombre();
        this.estado = sentenciado.getEstado().getNombre();
        this.curp = sentenciado.getCurp();
        this.estadoCivil = sentenciado.getEstadoCivil().getNombre();
        this.alias = sentenciado.getAlias();
        this.otrosNombres = sentenciado.getOtrosNombres();
        this.fechaNacimiento = sentenciado.getFechaNacimiento();
        this.ocupacion = sentenciado.getOcupacion().getNombre();
        this.sexo = sentenciado.getSexo().toString();
        this.etnia = sentenciado.getEtnia().getNombre();
        this.escolaridad = sentenciado.getEscolaridad().getNombre();
        this.telefonoFijo = sentenciado.getTelefonoFijo();
        this.celular = sentenciado.getCelular();
        this.email = sentenciado.getEmail();
    }

}

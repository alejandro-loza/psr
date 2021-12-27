package sspc.gob.mx.psr.dto;

import lombok.Getter;
import lombok.Setter;
import sspc.gob.mx.psr.model.Folio;
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

    String documento;

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

    String correoElectronico;


    public SentenciadoDto(Sentenciado sentenciado){
        this.id = sentenciado.getId().toString();
        this.folio = sentenciado.getFolio().toString();
        this.nombre = sentenciado.getNombre();
        this.apellidoPaterno = sentenciado.getApellidoPaterno();
        this.apellidoMaterno = sentenciado.getApellidoMaterno();
        this.nacionalidad = sentenciado.getNacionalidad().getNombre();
        this.estado = sentenciado.getEstado().getNombre();
        this.documento = sentenciado.getDocumento();
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
        this.correoElectronico = sentenciado.getCorreoElectronico();
    }

    public SentenciadoDto(Sentenciado sentenciado, Folio folio){
        this.id = sentenciado.getId().toString();
        this.folio = folio.getFolio();
        this.nombre = sentenciado.getNombre();
        this.apellidoPaterno = sentenciado.getApellidoPaterno();
        this.apellidoMaterno = sentenciado.getApellidoMaterno();
        this.nacionalidad = sentenciado.getNacionalidad().getNombre();
        this.estado = sentenciado.getEstado().getNombre();
        this.documento = sentenciado.getDocumento();
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
        this.correoElectronico = sentenciado.getCorreoElectronico();
    }

}

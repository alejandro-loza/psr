package mx.gob.oadprs.sicosel.dto;

import lombok.Getter;
import lombok.Setter;
import mx.gob.oadprs.sicosel.model.Folio;
import mx.gob.oadprs.sicosel.model.Sentenciado;

import java.time.LocalDate;


@Getter
@Setter
public class SentenciadoDto {

    Long ocupacionId;
    Long nacionalidadId;
    Long estadoId;
    Long estadoCivilId;
    Long etniaId;
    Long escolaridadId;

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
        this.nacionalidadId = sentenciado.getNacionalidad().getId();
        this.estado = sentenciado.getEstado().getNombre();
        this.estadoId = sentenciado.getEstado().getId();
        this.documento = sentenciado.getDocumento();
        this.estadoCivil = sentenciado.getEstadoCivil().getNombre();
        this.estadoCivilId = sentenciado.getEstadoCivil().getId();
        this.alias = sentenciado.getAlias();
        this.otrosNombres = sentenciado.getOtrosNombres();
        this.fechaNacimiento = sentenciado.getFechaNacimiento();
        this.ocupacion = sentenciado.getOcupacion() != null ?
                sentenciado.getOcupacion().getNombre(): null;
        this.ocupacionId = sentenciado.getOcupacion() != null ?
                sentenciado.getOcupacion().getId(): null;
        this.sexo = sentenciado.getSexo().toString();
        this.etnia = sentenciado.getEtnia().getNombre();
        this.etniaId = sentenciado.getEtnia().getId();
        this.escolaridad = sentenciado.getEscolaridad().getNombre();
        this.escolaridadId = sentenciado.getEscolaridad().getId();
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
        this.nacionalidadId = sentenciado.getNacionalidad().getId();
        this.estado = sentenciado.getEstado().getNombre();
        this.estadoId = sentenciado.getEstado().getId();
        this.documento = sentenciado.getDocumento();
        this.estadoCivil = sentenciado.getEstadoCivil().getNombre();
        this.estadoCivilId = sentenciado.getEstadoCivil().getId();
        this.alias = sentenciado.getAlias();
        this.otrosNombres = sentenciado.getOtrosNombres();
        this.fechaNacimiento = sentenciado.getFechaNacimiento();
        this.ocupacion = sentenciado.getOcupacion() != null ?
                sentenciado.getOcupacion().getNombre(): null;
        this.ocupacionId = sentenciado.getOcupacion() != null ?
                sentenciado.getOcupacion().getId(): null;
        this.sexo = sentenciado.getSexo().toString();
        this.etnia = sentenciado.getEtnia() != null?
                sentenciado.getEtnia().getNombre(): null;
        this.etniaId = sentenciado.getEtnia() != null?
                sentenciado.getEtnia().getId(): null;
        this.escolaridad = sentenciado.getEscolaridad() != null?
                sentenciado.getEscolaridad().getNombre(): null;
        this.escolaridadId = sentenciado.getEscolaridad() != null?
                sentenciado.getEscolaridad().getId() : null;
        this.telefonoFijo = sentenciado.getTelefonoFijo();
        this.celular = sentenciado.getCelular();
        this.correoElectronico = sentenciado.getCorreoElectronico();
    }

}

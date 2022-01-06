package sspc.gob.mx.psr.dto;

import lombok.Getter;
import lombok.Setter;
import sspc.gob.mx.psr.model.Folio;
import sspc.gob.mx.psr.model.Presentacion;
import sspc.gob.mx.psr.model.Sentenciado;

import java.time.LocalDate;


@Getter
@Setter
public class PresentacionDto {

    String id;

    String folioReporte;

    String idSentenciado;

    String folioSentenciado;

    String numReporte;

    String totalReporte;

    String periodoPresenta;

    LocalDate fchImpresion;

    LocalDate fchReporte;

    String nombre;

    String apellidoPaterno;

    String apellidoMaterno;

    String nacionalidad;

    String estadoCivil;

    String alias;

    String otrosNombres;

    LocalDate fechaNacimiento;

    String ocupacion;

    String sexo;

    int edad;

    String escolaridad;

    String telefonoFijo;

    String celular;

    String pais;

    String estado;

    String municipio;

    String colonia;

    String calle;

    String numero;

    String codigoPostal;

    String latitud;

    String longitud;

    String beneficio;

    String archivo;

    String fundamento;

    public PresentacionDto(Presentacion presentacion, Sentenciado sentenciado){
        this.id = presentacion.getId().toString();
        this.folioReporte = presentacion.getFolio().toString();
        this.numReporte = presentacion.numReporte().toString();
        this.totalReporte = presentacion.totalReporte().toString();
        this.periodoPresenta = presentacion.periodoPresenta().toString();
        this.fchImpresion = presentacion.fchImpresion().toDate();
        this.fchReporte = presentacion.fchReporte().toDateTime();
        this.idSentenciado = sentenciado.getId().toString();
        this.folioSentenciado = sentenciado.getFolio().toString();
        this.nombre = sentenciado.getNombre();
        this.apellidoPaterno = sentenciado.getApellidoPaterno();
        this.apellidoMaterno = sentenciado.getApellidoMaterno();
        this.nacionalidad = sentenciado.getNacionalidad().getNombre();
        this.estadoCivil = sentenciado.getEstadoCivil().getNombre();
        this.alias = sentenciado.getAlias();
        this.otrosNombres = sentenciado.getOtrosNombres();
        this.fechaNacimiento = sentenciado.getFechaNacimiento();
        this.ocupacion = sentenciado.getOcupacion().getNombre();
        this.sexo = sentenciado.getSexo().toString();
        this.edad = 1;
        this.escolaridad = sentenciado.getEscolaridad().getNombre();
        this.telefonoFijo = sentenciado.getTelefonoFijo();
        this.celular = sentenciado.getCelular();

    }


}

package sspc.gob.mx.psr.dto;

import lombok.Data;
import sspc.gob.mx.psr.model.Domicilio;

@Data
public class DomicilioDto {


    String estado;

    String pais;

    String municipio;

    String colonia;

    String calle;

    String numero;

    String codigoPostal;

    String latitud;

    String longitud;

    public DomicilioDto(Domicilio domicilio) {
        this.estado = domicilio.getEstado().getNombre();
        this.pais = domicilio.getPais().getNombre();
        this.municipio = domicilio.getMunicipio().getNombre();
        this.colonia = domicilio.getColonia();
        this.calle = domicilio.getCalle();
        this.numero = domicilio.getNumero();
        this.codigoPostal = domicilio.getCodigoPostal();
        this.latitud = domicilio.getLatitud();
        this.longitud = domicilio.getLongitud();
    }
}

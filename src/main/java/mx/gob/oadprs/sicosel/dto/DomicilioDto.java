package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.Domicilio;

import java.util.UUID;

@Data
public class DomicilioDto {

    UUID personaId;

    String estado;

    String pais;

    String municipio;

    String colonia;

    String calle;

    String numero;

    String codigoPostal;

    String latitud;

    String longitud;

    public DomicilioDto(Domicilio domicilio, UUID personaId) {
        this.personaId = personaId;
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

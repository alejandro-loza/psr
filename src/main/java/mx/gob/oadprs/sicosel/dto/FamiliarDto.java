package mx.gob.oadprs.sicosel.dto;

import lombok.Data;
import mx.gob.oadprs.sicosel.model.Familiar;

import java.util.UUID;

@Data
public class FamiliarDto {

    private UUID id;
    private UUID sentenciadoId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String documento;
    private String nacionalidad;
    private String telefonoFijo;
    private String celular;
    private String parentesco;

    public FamiliarDto(Familiar familiar) {
        this.id = familiar.getId();
        this.sentenciadoId = familiar.getSentenciado().getId();
        this.nombre = familiar.getNombre();
        this.apellidoPaterno = familiar.getApellidoPaterno();
        this.apellidoMaterno = familiar.getApellidoMaterno();
        this.documento = familiar.getDocumento();
        this.nacionalidad = familiar.getNacionalidad().getNombre();
        this.telefonoFijo = familiar.getTelefonoFijo();
        this.celular = familiar.getCelular();
        this.parentesco = familiar.getParentesco().getNombre();
    }


}

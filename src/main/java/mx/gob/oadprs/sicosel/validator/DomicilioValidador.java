package mx.gob.oadprs.sicosel.validator;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DomicilioValidador {

    @NotNull
    private Long estadoId;

    @NotNull
    private Long paisId;

    @NotNull
    private Long municipioId;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String colonia;

    @NotBlank
    @Size(min = 1, max = 50)
    private String calle;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 10)
    private String numero;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 5)
    private String codigoPostal;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 15)
    private String latitud;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 15)
    private String longitud;

    @NotBlank
    @Size(min = 1, max = 50)
    private String descripcion;

}

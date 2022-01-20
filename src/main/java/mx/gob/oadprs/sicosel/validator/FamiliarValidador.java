package mx.gob.oadprs.sicosel.validator;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public
class FamiliarValidador {

    @NotNull(message= "familiar.nombre.null")
    @NotBlank(message= "familiar.nombre.blank")
    private String nombre;

    @NotNull(message= "familiar.apellidoPaterno.null")
    @NotBlank(message= "familiar.apellidoPaterno.blank")
    @Size(min = 1, max = 50)
    private String apellidoPaterno;

    @NotNull(message= "familiar.apellidoMaterno.null")
    @NotBlank(message= "familiar.apellidoMaterno.blank")
    @Size(min = 1, max = 50)
    private String apellidoMaterno;

    @Size(min = 1, max = 18)
    private String documento;

    @NotNull(message= "familiar.nacionalidadId.null")
    Long nacionalidadId;

    private String telefonoFijo;

    private String celular;

    @NotNull(message= "familiar.parentesco.null")
    private Long parentescoId;
}

package sspc.gob.mx.psr.validator;

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

    @NotBlank(message= "familiar.apellidoPaterno.blank")
    @Size(min = 1, max = 50)
    private String apellidoPaterno;

    @NotBlank(message= "familiar.apellidoMaterno.blank")
    @Size(min = 1, max = 50)
    private String apellidoMaterno;

    @NotNull(message= "familiar.documento.null")
    @NotBlank(message= "familiar.documento.blank")
    @Size(min = 1, max = 18)
    private String documento;

    @NotNull(message= "familiar.telefonoFijo.null")
    private String telefonoFijo;

    @NotNull(message= "familiar.celular.null")
    @NotBlank(message= "familiar.celular.blank")
    private String celular;

    @NotNull(message= "familiar.parentesco.null")
    private Long parentescoId;
}

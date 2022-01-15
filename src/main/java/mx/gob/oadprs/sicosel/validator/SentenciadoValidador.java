package mx.gob.oadprs.sicosel.validator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.gob.oadprs.sicosel.enums.Sexo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class SentenciadoValidador {
    @NotNull(message= "sentenced.name.null")
    @NotBlank(message= "sentenced.name.blank")
    @Size(min = 1, max = 100)
    String nombre;

    @Size(min = 1, max = 100)
    String apellidoPaterno;

    @Size(min = 1, max = 100)
    String apellidoMaterno;

    @NotNull(message= "sentenced.nacionalidadId.null")
    Long nacionalidadId;

    @NotNull
    Long estadoId;

    @NotNull(message= "sentenced.documento.null")
    @NotBlank(message= "sentenced.documento.blank")
    @Size(min = 1, max = 18)
    String documento;

    @NotNull(message= "sentenced.estadoCivil.null")
    Long estadoCivil;

    @Size(min = 1, max = 100)
    String alias;

    @Size(min = 1, max = 100)
    String otrosNombres;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @NotNull(message= "sentenced.fechaNacimiento.null")
    LocalDate fechaNacimiento;

    Long ocupacionId;

    @NotNull(message= "sentenced.sexo.null")
    Sexo sexo;

    Long etniaId;

    Long escolaridad;


    @Size(min = 1, max = 50)
    String telefonoFijo;

    @NotNull(message= "sentenced.celular.null")
    @NotBlank(message= "sentenced.celular.blank")
    @Size(min = 10, max = 10)
    String celular;

    @Email
    @NotNull(message= "sentenced.correoElectronico.null")
    @NotBlank(message= "sentenced.correoElectronico.blank")
    String correoElectronico;
}

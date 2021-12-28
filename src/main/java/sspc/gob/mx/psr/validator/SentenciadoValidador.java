package sspc.gob.mx.psr.validator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sspc.gob.mx.psr.enums.Sexo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class SentenciadoValidador {
    @NotNull(message= "sentenced.name.null")
    @NotBlank
    @Size(min = 1, max = 100)
    String nombre;

    @NotBlank
    @Size(min = 1, max = 100)
    String apellidoPaterno;

    @NotBlank
    @Size(min = 1, max = 100)
    String apellidoMaterno;

    @NotNull
    Long nacionalidadId;

    @NotNull
    Long estadoId;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 18)
    String documento;

    @NotNull
    Long estadoCivil;

    @NotBlank
    @Size(min = 1, max = 100)
    String alias;

    @NotBlank
    @Size(min = 1, max = 100)
    String otrosNombres;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate fechaNacimiento;

    Long ocupacionId;

    @NotNull
    Sexo sexo;

    @NotNull
    Long etniaId;

    @NotNull
    Long escolaridad;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    String telefonoFijo;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    String celular;

    @Email
    @NotNull
    @NotBlank
    String correoElectronico;
}

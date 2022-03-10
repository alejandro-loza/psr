package mx.gob.oadprs.sicosel.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Builder
public class SentenciadoCriteria {
    private Optional<String> nombre;
    private Optional<String> apellidoPaterno;
    private Optional<String> apellidoMaterno;
    private Optional<String> alias;
    private Optional<String> otrosNombres;
    private Optional<String> nombrePadres;
    private Optional<String> apellidoPaternoPadres;
    private Optional<String> apellidoMaternoPadres;
    private Optional<Long> nacionalidad;
    private Optional<Long> ocupacion;
    private Optional<String> folio;
    private Optional<UUID> id;


}

package sspc.gob.mx.psr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import sspc.gob.mx.psr.enums.Sexo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Folio {
    private static final int COCIENTE_EXTRA = 7;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCodigo;
    private Long nacimientoCodigo;
    private Long entidadCodigo;
    private Character sexoCodigo;
    private String nacionalidadCodigo;
    private Long consecutivo;
    private Long extra;


}
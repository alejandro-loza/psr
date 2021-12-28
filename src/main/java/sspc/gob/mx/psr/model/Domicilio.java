package sspc.gob.mx.psr.model;

import lombok.*;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Municipio;
import sspc.gob.mx.psr.model.catalog.Pais;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Domicilio extends EntidadBase {
    @Id
    @GeneratedValue
    @Column(name = "id_domicilio")
    private UUID id;

    @OneToOne
    @JoinColumn(name="id_persona")
    private Sentenciado sentenciado;//todo persona

    @ManyToOne
    @JoinColumn(name = "fk_id_cat_estado", nullable = false, updatable = false )
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "fk_id_cat_pais", nullable = false, updatable = false )
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "fk_id_cat_municipio", nullable = false, updatable = false )
    private Municipio municipio;

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

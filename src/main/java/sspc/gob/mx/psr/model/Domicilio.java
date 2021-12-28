package sspc.gob.mx.psr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Municipio;
import sspc.gob.mx.psr.model.catalog.Pais;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
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
    @JoinColumn(name = "fk_id_cat_estado", nullable = false, updatable = false )
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "fk_id_cat_estado", nullable = false, updatable = false )
    private Municipio municipio;

    @NotNull
    @NotBlank
    private String colonia;

    @NotBlank
    private String calle;

    @NotNull
    @NotBlank
    private String numero;

    @NotNull
    @NotBlank
    private String latitud;

    @NotNull
    @NotBlank
    private String longitud;



}

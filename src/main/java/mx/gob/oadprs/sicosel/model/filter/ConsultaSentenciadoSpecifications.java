package mx.gob.oadprs.sicosel.model.filter;

import mx.gob.oadprs.sicosel.model.ConsultaSentenciado;
import mx.gob.oadprs.sicosel.model.ConsultaSentenciado_;
import mx.gob.oadprs.sicosel.utils.SentenciadoCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class ConsultaSentenciadoSpecifications {

    public ConsultaSentenciadoSpecifications() {
    }

    public static Specification<ConsultaSentenciado> creaSentenciadoSpecifications(SentenciadoCriteria criterios) {
        return nombreLike(criterios.getNombre())
                .and(apellidoPaternoLike(criterios.getApellidoPaterno()))
                .and(apellidoMaternoLike(criterios.getApellidoMaterno()))
                .and(aliasLike(criterios.getAlias()))
                .and(otrosNombresLike((criterios.getOtrosNombres())))
                .and(nombrePadresLike(criterios.getNombrePadres()))
                .and(apellidoPaternoPadresLike(criterios.getApellidoPaternoPadres()))
                .and(apellidoMaternoPadresLike(criterios.getApellidoMaternoPadres()))
                .and(nacionalidadEqualTo(criterios.getNacionalidad()))
                .and(folioLike(criterios.getFolio()))
                .and(fechaNacimientoEqualsTo(criterios.getFechaNacimiento()))
                .and(idEqualsTo(criterios.getId()))
                .and(ocupacionEqualTo(criterios.getOcupacion()));
    }

    public static Specification<ConsultaSentenciado> nombreLike(Optional<String> nombre) {
        return (root, query, builder) -> {
            return nombre.map(ti ->
                    builder.like(root.get(ConsultaSentenciado_.nombre), "%" + String.valueOf(ti) + "%")
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> apellidoPaternoLike(Optional<String> apellidoPaterno) {
        return (root, query, builder) -> {
            return apellidoPaterno.map(ti ->
                    builder.like(root.get(ConsultaSentenciado_.apellidoPaterno), "%" + String.valueOf(ti) + "%")
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> apellidoMaternoLike(Optional<String> apellidoMaterno) {
        return (root, query, builder) -> {
            return apellidoMaterno.map(ti ->
                    builder.like(root.get(ConsultaSentenciado_.apellidoMaterno), "%" + String.valueOf(ti) + "%")
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> aliasLike(Optional<String> alias) {
        return (root, query, builder) -> {
            return alias.map(ti ->
                    builder.like(root.get(ConsultaSentenciado_.alias), "%" + String.valueOf(ti) + "%")
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> otrosNombresLike(Optional<String> otros) {
        return (root, query, builder) -> {
            return otros.map(ti ->
                    builder.like(root.get(ConsultaSentenciado_.otrosNombres), "%" + String.valueOf(ti) + "%")
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> nombrePadresLike(Optional<String> nombre) {
        return (root, query, builder) -> {
            return nombre.map(ti ->
                    builder.like(root.get(ConsultaSentenciado_.nombrePadres), "%" + String.valueOf(ti) + "%")
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> apellidoPaternoPadresLike(Optional<String> apellido) {
        return (root, query, builder) -> {
            return apellido.map(ti ->
                    builder.like(root.get(ConsultaSentenciado_.apellidoPaternoPadres), "%" + String.valueOf(ti) + "%")
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> apellidoMaternoPadresLike(Optional<String> apellido) {
        return (root, query, builder) -> {
            return apellido.map(ti ->
                    builder.like(root.get(ConsultaSentenciado_.apellidoMaternoPadres), "%" + String.valueOf(ti) + "%")
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> nacionalidadEqualTo(Optional<Long> nacionalidad) {
        return (root, query, builder) -> {
            return nacionalidad.map(pais -> builder.equal(root.get(ConsultaSentenciado_.nacionalidad), pais)
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> ocupacionEqualTo(Optional<Long> ocupacionId) {
        return (root, query, builder) -> {
            return ocupacionId.map(ocupacion -> builder.equal(root.get(ConsultaSentenciado_.ocupacion), ocupacion)
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> folioLike(Optional<String> folio) {
        return (root, query, builder) -> {
            return folio.map(ti ->
                    builder.like(root.get(ConsultaSentenciado_.folio), "%" + String.valueOf(ti) + "%")
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> idEqualsTo(Optional<UUID> id) {
        return (root, query, builder) -> {
            return id.map(i -> builder.equal(root.get(ConsultaSentenciado_.id), i)
            ).orElse(null);
        };
    }

    public static Specification<ConsultaSentenciado> fechaNacimientoEqualsTo(Optional<String> fechaNacimiento) {
        return (root, query, builder) -> {
            return fechaNacimiento.map(i -> builder.equal(root.get(ConsultaSentenciado_.fechaNacimiento), LocalDate.parse(i) )
            ).orElse(null);
        };
    }

}

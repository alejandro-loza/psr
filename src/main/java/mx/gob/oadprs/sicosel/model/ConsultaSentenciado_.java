package mx.gob.oadprs.sicosel.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ConsultaSentenciado.class)
public class ConsultaSentenciado_ {
    public static volatile SingularAttribute<ConsultaSentenciado, String> id;
    public static volatile SingularAttribute<ConsultaSentenciado, String> nombre;
    public static volatile SingularAttribute<ConsultaSentenciado, String> apellidoPaterno;
    public static volatile SingularAttribute<ConsultaSentenciado, String> apellidoMaterno;
    public static volatile SingularAttribute<ConsultaSentenciado, String> alias;
    public static volatile SingularAttribute<ConsultaSentenciado, String> otrosNombres;
    public static volatile SingularAttribute<ConsultaSentenciado, String> nombrePadres;
    public static volatile SingularAttribute<ConsultaSentenciado, String> apellidoPaternoPadres;
    public static volatile SingularAttribute<ConsultaSentenciado, String> apellidoMaternoPadres;
    public static volatile SingularAttribute<ConsultaSentenciado, String> folio;
    public static volatile SingularAttribute<ConsultaSentenciado, Long> nacionalidad;
    public static volatile SingularAttribute<ConsultaSentenciado, Long> ocupacion;

}

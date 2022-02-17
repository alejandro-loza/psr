package mx.gob.oadprs.sicosel.enums;

public enum Sexo {
    MASCULINO('H'), FEMENINO('M');

    private final char codigo;

    Sexo(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }
}

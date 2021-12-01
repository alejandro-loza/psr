package sspc.gob.mx.psr.enums;

public enum Gender {
    MALE('H'), FEMALE('M');

    private final char code;

    Gender(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }
}
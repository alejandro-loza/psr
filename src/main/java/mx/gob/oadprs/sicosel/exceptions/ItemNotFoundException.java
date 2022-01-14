package mx.gob.oadprs.sicosel.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super( message );
    }
}

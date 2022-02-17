package mx.gob.oadprs.sicosel.exeptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super( message );
    }
}

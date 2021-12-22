package sspc.gob.mx.psr.exeptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super( message );
    }
}

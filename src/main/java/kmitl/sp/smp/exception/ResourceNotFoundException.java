package kmitl.sp.smp.exception;

/**
 * Created by Jo on 3/27/2017.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message,Throwable cause) {
        super(message, cause);
    }
}

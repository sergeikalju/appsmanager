package sk.appsmanager.exception;

@SuppressWarnings("serial")
public class ControllerRuntimeException extends RuntimeException {

    public ControllerRuntimeException() {
        super();
    }

    public ControllerRuntimeException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ControllerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerRuntimeException(String message) {
        super(message);
    }

    public ControllerRuntimeException(Throwable cause) {
        super(cause);
    }
}

package by.epamtc.jwd.auth.web.exception;

// TODO ??? DELETE
public class ControllerException extends Exception {

    private static final long serialVersionUID = 4578342278296044396L;

    public ControllerException() {
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }
}

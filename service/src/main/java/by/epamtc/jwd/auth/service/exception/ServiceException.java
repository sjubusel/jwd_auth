package by.epamtc.jwd.auth.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 6077262820400756530L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}

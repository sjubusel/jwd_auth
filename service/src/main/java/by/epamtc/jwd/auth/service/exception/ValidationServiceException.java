package by.epamtc.jwd.auth.service.exception;

// TODO ??? delete
public class ValidationServiceException extends ServiceException {

    private static final long serialVersionUID = -1148140583570924769L;

    public ValidationServiceException() {
    }

    public ValidationServiceException(String message) {
        super(message);
    }

    public ValidationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationServiceException(Throwable cause) {
        super(cause);
    }
}

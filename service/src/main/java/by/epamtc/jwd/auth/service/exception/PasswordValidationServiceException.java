package by.epamtc.jwd.auth.service.exception;

// TODO ??? delete
public class PasswordValidationServiceException extends ValidationServiceException {
    private static final long serialVersionUID = 7369400807621003433L;

    public PasswordValidationServiceException() {
    }

    public PasswordValidationServiceException(String message) {
        super(message);
    }

    public PasswordValidationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordValidationServiceException(Throwable cause) {
        super(cause);
    }
}

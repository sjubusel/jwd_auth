package by.epamtc.jwd.auth.service.exception;

public class AuthDataValidationServiceException extends ValidationServiceException {

    private static final long serialVersionUID = 6696806372677070381L;

    public AuthDataValidationServiceException() {
    }

    public AuthDataValidationServiceException(String message) {
        super(message);
    }

    public AuthDataValidationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthDataValidationServiceException(Throwable cause) {
        super(cause);
    }
}

package by.epamtc.jwd.auth.service.exception;

public class LoginValidationServiceException extends ValidationServiceException {
    private static final long serialVersionUID = 3689489419174753244L;

    public LoginValidationServiceException() {
    }

    public LoginValidationServiceException(String message) {
        super(message);
    }

    public LoginValidationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginValidationServiceException(Throwable cause) {
        super(cause);
    }
}

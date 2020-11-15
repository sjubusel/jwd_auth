package by.epamtc.jwd.auth.service.exception;

public class AllergicServiceException extends ServiceException {
    private static final long serialVersionUID = 8783868093786169447L;

    public AllergicServiceException() {
    }

    public AllergicServiceException(String message) {
        super(message);
    }

    public AllergicServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AllergicServiceException(Throwable cause) {
        super(cause);
    }
}

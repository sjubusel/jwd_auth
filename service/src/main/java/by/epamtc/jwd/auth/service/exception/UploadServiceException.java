package by.epamtc.jwd.auth.service.exception;

public class UploadServiceException extends ServiceException {
    private static final long serialVersionUID = -5878463710342585209L;

    public UploadServiceException() {
    }

    public UploadServiceException(String message) {
        super(message);
    }

    public UploadServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadServiceException(Throwable cause) {
        super(cause);
    }
}

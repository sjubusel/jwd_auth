package by.epamtc.jwd.auth.dao.exception;

public class DaoException extends Exception {
    private static final long serialVersionUID = -1262557244172368385L;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}

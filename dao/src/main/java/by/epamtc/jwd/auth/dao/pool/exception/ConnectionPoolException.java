package by.epamtc.jwd.auth.dao.pool.exception;

public class ConnectionPoolException extends Exception {
    private static final long serialVersionUID = -5170737677181367816L;

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}

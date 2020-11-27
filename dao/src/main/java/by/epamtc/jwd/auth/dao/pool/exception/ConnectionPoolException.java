package by.epamtc.jwd.auth.dao.pool.exception;

/**
 * a class-exception which is used to show a dao interface an error while
 * taking, releasing a sql connection
 */
public class ConnectionPoolException extends Exception {
    /**
     * a static field which is used as this exception
     * implements java.io.Serializable
     */
    private static final long serialVersionUID = -5170737677181367816L;

    /**
     * a constructor with no arguments
     */
    public ConnectionPoolException() {
    }

    /**
     * a standard exception-constructor with a String argument
     *
     * @param message a String, which is a message applied in a process
     *                of exception handling
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * a standard exception-constructor with a String argument
     * and a "cause"-exception as an argument
     *
     * @param message a String, which is a message applied in a process
     *                of exception handling
     * @param cause   an exception, which caused this exception
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * a standard exception-constructor with a "cause"-exception as an argument
     *
     * @param cause an exception, which caused this exception
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}

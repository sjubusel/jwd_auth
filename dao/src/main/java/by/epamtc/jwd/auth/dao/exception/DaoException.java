package by.epamtc.jwd.auth.dao.exception;

/**
 * a class-exception which is an only possible exception to interact between
 * dao and service layers
 */
public class DaoException extends Exception {
    /**
     * a static field which is used as this exception
     * implements java.io.Serializable
     */
    private static final long serialVersionUID = -1262557244172368385L;

    /**
     * a constructor with no arguments
     */
    public DaoException() {
    }

    /**
     * a standard exception-constructor with a String argument
     *
     * @param message a String, which is a message applied in a process
     *                of exception handling
     */
    public DaoException(String message) {
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
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * a standard exception-constructor with a "cause"-exception as an argument
     *
     * @param cause   an exception, which caused this exception
     */
    public DaoException(Throwable cause) {
        super(cause);
    }
}

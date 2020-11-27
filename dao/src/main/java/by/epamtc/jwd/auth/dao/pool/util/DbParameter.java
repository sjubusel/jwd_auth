package by.epamtc.jwd.auth.dao.pool.util;

/**
 * a final class which contains constants used during an initialization of
 * ConnectionPool class
 *
 * @see by.epamtc.jwd.auth.dao.pool.ConnectionPool
 */
public final class DbParameter {
    /**
     * a field, which is a constant to retrieve a driver to connect to a
     * current data source
     */
    public static final String DB_DRIVER = "driver";

    /**
     * a field which is a constant to retrieve an url to connect to a current
     * data source
     */
    public static final String DB_URL = "url";

    /**
     * a field which is a constant containing a credential named as "login"
     */
    public static final String DB_LOGIN = "login";

    /**
     * a field which is a constant containing a credential named as "password"
     */
    public static final String DB_PASSWORD = "password";

    /**
     * a field which is a constant that represents a size of a ConnectionPool
     */
    public static final String DB_POOL_SIZE = "poolSize";

    /**
     * a private constructor with no arguments in order to eliminate a
     * possibility to create an instance of this class
     */
    private DbParameter() {
    }
}

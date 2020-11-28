package by.epamtc.jwd.auth.dao.pool.util;

import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.model.constant.AppConstant;

import java.util.ResourceBundle;

/**
 * a class which facilitates a process of initialization of ConnectionPool.
 * It is of a Singleton pattern.
 *
 * @see ConnectionPool
 */
public final class DbResourceManager {
    /**
     * a unique and only instance of this class
     */
    private static volatile DbResourceManager instance;

    /**
     * a field which represent the main class, which facilitates a process
     * of initialization of ConnectionPool
     */
    private ResourceBundle bundle = ResourceBundle.getBundle(AppConstant
            .DEFAULT_BUNDLE);

    /**
     * a private constructor of a Singleton pattern
     */
    private DbResourceManager() {
    }

    /**
     * a method which initialize and provide thread safe instance of a
     * current type
     *
     * @return an unique and only instance of a current class
     */
    public static DbResourceManager getInstance() {
        DbResourceManager localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DbResourceManager();
                }
            }
        }
        return localInstance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}

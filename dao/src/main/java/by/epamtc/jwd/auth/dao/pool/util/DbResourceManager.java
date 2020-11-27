package by.epamtc.jwd.auth.dao.pool.util;

import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.model.constant.AppConstant;

import java.util.ResourceBundle;

public class DbResourceManager {
    private static volatile DbResourceManager instance;

    private ResourceBundle bundle = ResourceBundle.getBundle(AppConstant
            .DEFAULT_BUNDLE);

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

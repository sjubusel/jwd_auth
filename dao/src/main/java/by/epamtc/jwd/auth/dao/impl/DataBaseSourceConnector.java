package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.SourceConnector;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataBaseSourceConnector implements SourceConnector {
    private static volatile DataBaseSourceConnector instance;

    private final ComboPooledDataSource pool;

    private DataBaseSourceConnector() {
        loadDatabaseDriver();
        pool = new ComboPooledDataSource();
        getAndSetDataBaseCredentials();
        configurePoolSettings();
    }

    private static void loadDatabaseDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAndSetDataBaseCredentials() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String url = resourceBundle.getString("url");
        String login = resourceBundle.getString("login");
        String password = resourceBundle.getString("password");
        pool.setJdbcUrl(url);
        pool.setUser(login);
        pool.setPassword(password);
    }

    private void configurePoolSettings() {
        pool.setMinPoolSize(3);
        pool.setAcquireIncrement(3);
        pool.setMaxPoolSize(10);
        pool.setMaxStatements(100);
    }

    public static DataBaseSourceConnector getInstance() {
        DataBaseSourceConnector localInstance = instance;
        if (localInstance == null) {
            synchronized (DataBaseSourceConnector.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataBaseSourceConnector();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }
}

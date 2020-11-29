package by.epamtc.jwd.auth.dao.pool;

import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @DisplayName("test whether a connection provided by ConnectionPool")
    @Test
    void testTakeConnection() throws ConnectionPoolException, SQLException {
        Connection connection = connectionPool.takeConnection();
        Assertions.assertTrue(connection.isValid(2000));
        connection.close();
    }
}
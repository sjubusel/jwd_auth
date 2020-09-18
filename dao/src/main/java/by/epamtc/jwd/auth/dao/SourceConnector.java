package by.epamtc.jwd.auth.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface SourceConnector {
    Connection getConnection() throws SQLException;
}

package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.UploadDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultUploadDao implements UploadDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public boolean updatePatientPhoto(String targetFileName, AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement.UPDATE_PHOTO_PATH);
            statement.setString(1, targetFileName);
            statement.setInt(2, user.getUserId());
            return statement.executeUpdate() > 0;
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during updating of patient photo", e);
        } catch (SQLException e) {
            throw new DaoException("An error while updating of patient photo", e);
        } finally {
            pool.closeConnection(conn, statement);
        }
    }
}

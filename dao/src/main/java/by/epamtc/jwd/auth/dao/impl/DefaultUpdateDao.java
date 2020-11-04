package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.UpdateDao;
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

public class DefaultUpdateDao implements UpdateDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public boolean updatePatientPhoto(String targetFileName, AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        PatientInfo patientInfo = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement.SELECT_PATIENT_INFO);
            statement.setInt(1, user.getUserId());
//        rSet = statement.executeQuery();
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during updating of patient photo", e);
        } catch (SQLException e) {
            throw new DaoException("An error while updating of patient photo", e);
        }

        return false;
    }
}

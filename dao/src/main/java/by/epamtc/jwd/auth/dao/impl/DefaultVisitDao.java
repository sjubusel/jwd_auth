package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.VisitDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DefaultVisitDao implements VisitDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public boolean registerVisit(AdmissionDepartmentVisit hospVisit)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement.INSERT_VISIT);

            statement.setInt(1, Integer.parseInt(hospVisit
                    .getPatientShortInfo()));
            statement.setString(2, hospVisit.getVisitReason().name());
            statement.setString(3, hospVisit.getPatientVisitDescriptionInfo());
            statement.setString(4, hospVisit.getTransportationStatus().name());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while registering " +
                    "(AdmissionDepartmentVisit)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during registration of" +
                    "AdmissionDepartmentVisit", e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }
}

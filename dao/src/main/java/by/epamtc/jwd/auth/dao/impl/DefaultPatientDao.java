package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.PatientDao;
import by.epamtc.jwd.auth.dao.VisitDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultPatientDao implements PatientDao {
    private ConnectionPool pool = ConnectionPool.getInstance();
    /*toDO REPLACE WITH VisitRelatedCompiler*/
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private VisitDao visitDao = daoFactory.getVisitDao();

    @Override
    public AdmissionDepartmentVisit fetchFullVisitIfExist(AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        AdmissionDepartmentVisit visit = new AdmissionDepartmentVisit();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement.);
            statement.setInt(1, user.getUserId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                visit = visitDao.(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException(e)
        } catch (ConnectionPoolException e) {
            throw new DaoException(e)
        }

        return visit;
    }
}

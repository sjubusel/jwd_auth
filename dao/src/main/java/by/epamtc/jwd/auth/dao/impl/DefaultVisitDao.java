package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.VisitDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultVisitDao implements VisitDao {
    public static ReentrantLock lock = new ReentrantLock();
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

    @Override
    public List<AdmissionDepartmentVisit> fetchNewVisits(AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<AdmissionDepartmentVisit> shortenVisits = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement.FETCH_NEW_VISITS);
            LocalDateTime dayBefore = LocalDateTime.now().minusDays(1);
            statement.setTimestamp(1, Timestamp.valueOf(dayBefore));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AdmissionDepartmentVisit visit = compileShortenedVisit(resultSet);
                shortenVisits.add(visit);
            }

        } catch (SQLException e) {
            throw new DaoException("An error while fetching " +
                    "AdmissionDepartmentVisit-s", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during fetching of" +
                    "AdmissionDepartmentVisit", e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return shortenVisits;
    }

    @Override
    public boolean acceptPatientForTreatment(String visitId, AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement[] statements = new PreparedStatement[2];
        ResultSet resultSet = null;
        int pointer = 0;
        lock.lock();

        try {
            conn = pool.takeConnection();
            statements[pointer] = conn.prepareStatement(SqlStatement
                    .SELECT_RESPONSIBLE_DOCTOR);
            resultSet = statements[pointer].executeQuery();
            if (resultSet.next()) {
                int responsibleDoctorId = resultSet.getInt(1);
                if (responsibleDoctorId != 0) {
                    return false;
                }
            }

            statements[++pointer] = conn.prepareStatement(SqlStatement
                    .INSERT_RESPONSIBLE_DOCTOR);
            statements[pointer].setInt(1, user.getStaffId());
            statements[pointer].setInt(2, Integer.parseInt(visitId));
            statements[pointer].executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while accepting a patient for " +
                    "treatment", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection" +
                    " from a Connection pool", e);
        } finally {
            lock.unlock();
            pool.closeConnection(conn, statements, resultSet);
        }

        return true;
    }

    private AdmissionDepartmentVisit compileShortenedVisit(ResultSet resultSet)
            throws SQLException {
        AdmissionDepartmentVisit visit = new AdmissionDepartmentVisit();
        int visitId = resultSet.getInt(1);
        visit.setVisitId(visitId);
        Timestamp timestamp = resultSet.getTimestamp(2);
        LocalDateTime visitDateTime = timestamp != null
                                      ? timestamp.toLocalDateTime()
                                      : null;
        visit.setVisitDateTime(visitDateTime);
        String lastName = resultSet.getString(3);
        String firstName = resultSet.getString(4);
        String middleName = resultSet.getString(5);
        String personInfo = middleName != null
                            ? lastName + AppConstant.ONE_WHITESPACE + firstName
                                    + AppConstant.ONE_WHITESPACE + middleName
                            : lastName + AppConstant.ONE_WHITESPACE + firstName;
        visit.setPatientShortInfo(personInfo);
        String visitDescription = resultSet.getString(6);
        visit.setPatientVisitDescriptionInfo(visitDescription);
        return visit;
    }
}

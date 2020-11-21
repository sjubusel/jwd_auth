package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.PatientDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.dao.util.VisitRelatedEntitiesCompiler;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultPatientDao implements PatientDao {
    private ConnectionPool pool = ConnectionPool.getInstance();
    private VisitRelatedEntitiesCompiler visitRelatedEntitiesCompiler
            = VisitRelatedEntitiesCompiler.getInstance();

    @Override
    public AdmissionDepartmentVisit fetchFullVisitIfExist(AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        AdmissionDepartmentVisit visit = new AdmissionDepartmentVisit();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_FULL_VISIT_BY_PERSON_ID);
            statement.setInt(1, user.getUserId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                visit = visitRelatedEntitiesCompiler.compileShortenedVisit(
                        resultSet);
                visit = visitRelatedEntitiesCompiler.compileFullVisit(visit,
                        resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException("An error while fetching a full visit " +
                    "for a patient if the current visit exists.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "during fetching a full visit for a patient if " +
                    "the current visit exists. ", e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return visit;
    }

    @Override
    public List<MedicinePrescription> fetchAllNewMedicinePrescriptions(
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<MedicinePrescription> prescriptions = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_MEDICINE_PRESCRIPTIONS_BY_PATIENT_ID);
            statement.setInt(1, user.getUserId());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MedicinePrescription prescription = visitRelatedEntitiesCompiler
                        .compileMedicinePrescription(resultSet);
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            throw new DaoException("An error (SQLException) while fetching " +
                    "all medicine unexecuted per current visit prescriptions " +
                    "by patientId. PatientId=" + user.getUserId(), e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error (ConnectionPoolException" +
                    " while taking a connection in order to fetch " +
                    "all medicine unexecuted per current visit prescriptions " +
                    "by patientId. PatientId=" + user.getUserId(), e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return prescriptions;
    }
}

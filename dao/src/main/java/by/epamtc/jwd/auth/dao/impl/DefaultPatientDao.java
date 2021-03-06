package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.PatientDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.dao.util.VisitRelatedEntitiesCompiler;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.med_info.DepartmentOrigin;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.RefusalReference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
                        resultSet, 1);
                visit = visitRelatedEntitiesCompiler.compileFullVisit(visit,
                        resultSet, 7);
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

    @Override
    public int fetchPatientIdByMedicinePrescriptionId(String prescriptionId)
            throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .SELECT_PATIENT_ID_BY_MED_PRESCRIPTION_ID);
            statement.setInt(1, Integer.parseInt(prescriptionId));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching a patient id by " +
                    "prescription id", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection in " +
                    "order to fetch a patient id by " +
                    "prescription id", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }

        return -1;
    }

    @Override
    public boolean disagreeWithMedicinePrescription(String prescriptionId,
            String disagreementDescription) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .UPDATE_DISAGREEMENT_WITH_MED_PRESCRIPTION);
            statement.setString(1, disagreementDescription);
            statement.setInt(2, Integer.parseInt(prescriptionId));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while saving information " +
                    "about disagreement with a medicine prescription", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "in order to disagree with a medicine prescription", e);
        } finally {
            pool.closeConnection(connection, statement);
        }

        return true;
    }

    @Override
    public List<Prescription> fetchAllNewPrescriptions(AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Prescription> prescriptions = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_NON_MEDICINE_PRESCRIPTIONS_BY_PATIENT_ID);
            statement.setInt(1, user.getUserId());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Prescription prescription = visitRelatedEntitiesCompiler
                        .compileNonMedicinePrescription(resultSet);
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            throw new DaoException("An error (SQLException) while fetching " +
                    "all non-medicine unexecuted per current visit prescriptions " +
                    "by patientId. PatientId=" + user.getUserId(), e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error (ConnectionPoolException" +
                    " while taking a connection in order to fetch " +
                    "all non-medicine unexecuted per current visit prescriptions " +
                    "by patientId. PatientId=" + user.getUserId(), e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return prescriptions;
    }

    @Override
    public int fetchPatientIdByPrescriptionId(String prescriptionId)
            throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .SELECT_PATIENT_ID_BY_PRESCRIPTION_ID);
            statement.setInt(1, Integer.parseInt(prescriptionId));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching a patient id by " +
                    "prescription id", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection in " +
                    "order to fetch a patient id by " +
                    "prescription id", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }

        return -1;
    }

    @Override
    public boolean disagreeWithPrescription(String prescriptionId,
            String disagreementDescription) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .UPDATE_DISAGREEMENT_WITH_PRESCRIPTION);
            statement.setString(1, disagreementDescription);
            statement.setInt(2, Integer.parseInt(prescriptionId));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while saving information " +
                    "about disagreement with a non-medicine prescription", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "in order to disagree with a non-medicine prescription", e);
        } finally {
            pool.closeConnection(connection, statement);
        }

        return true;
    }

    @Override
    public int fetchPatientIdByVisitId(int visitId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .SELECT_PATIENT_ID_BY_VISIT_ID);
            statement.setInt(1, visitId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching a patient id by " +
                    "a visit id", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection in " +
                    "order to fetch a patient id by a visit id", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }
        return -1;
    }

    @Override
    public List<Diagnosis> fetchDiagnosesForPatientDuringVisit(int visitId)
            throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Diagnosis> diagnoses = new ArrayList<>();

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .SELECT_DIAGNOSIS_BY_VISIT_ID);
            statement.setInt(1, visitId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Diagnosis diagnosis = visitRelatedEntitiesCompiler.compileDiagnosis(
                        resultSet, DepartmentOrigin.ADMISSION_DEPARTMENT);
                diagnoses.add(diagnosis);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching diagnoses " +
                    "during a visit", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "in order to fetch diagnoses during a visit", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }
        return diagnoses;
    }

    @Override
    public List<MedicinePrescription> fetchMedPrescriptionsFinishedDuringVisit(
            int visitId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<MedicinePrescription> prescriptions = new ArrayList<>();

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .SELECT_FINISHED_MEDICINE_PRESCRIPTIONS_BY_VISIT);
            statement.setInt(1, visitId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MedicinePrescription prescription = visitRelatedEntitiesCompiler
                        .compileMedicinePrescription(resultSet);
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching medicine " +
                    "prescriptions finished during a visit", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "in order to fetch finished medicine prescriptions " +
                    "finished during a visit", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }
        return prescriptions;
    }

    @Override
    public List<Prescription> fetchPrescriptionsFinishedDuringVisit(int visitId)
            throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Prescription> prescriptions = new ArrayList<>();

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .SELECT_FINISHED_PRESCRIPTIONS_BY_VISIT);
            statement.setInt(1, visitId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Prescription prescription = visitRelatedEntitiesCompiler
                        .compileNonMedicinePrescription(resultSet);
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching non-medicine " +
                    "prescriptions finished during a visit", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "in order to fetch finished non-medicine prescriptions " +
                    "finished during a visit", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }
        return prescriptions;
    }

    @Override
    public List<RefusalReference> fetchRefusalReferences(String pageNumber,
            AuthUser user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int pageId = Integer.parseInt(pageNumber) - 1;
        List<RefusalReference> references = new ArrayList<>();

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .SELECT_REFUSAL_REFERENCES_BY_PATIENT_ID);
            statement.setInt(1, user.getUserId());
            statement.setInt(2, Math.max(0, pageId) * AppConstant
                    .REFERENCES_PER_PAGE);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RefusalReference reference = new RefusalReference();

                int refusalReferenceId = resultSet.getInt(1);
                reference.setRefusalReferenceId(refusalReferenceId);

                LocalDateTime referenceDatetime = null;
                Timestamp referenceTimestamp = resultSet.getTimestamp(2);
                if (referenceTimestamp != null) {
                    referenceDatetime = referenceTimestamp.toLocalDateTime();
                }
                reference.setReferenceDatetime(referenceDatetime);

                AdmissionDepartmentVisit visitInfo = visitRelatedEntitiesCompiler
                        .compileShortenedVisit(resultSet, 3);
                reference.setVisitInfo(visitInfo);

                references.add(reference);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching all refusal " +
                    "references by a patient id.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "in order to fetch all refusal references by a " +
                    "patient id.", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }

        return references;
    }

    @Override
    public int fetchPatientIdByReferenceId(String referenceId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .SELECT_PATIENT_ID_BY_REFERENCE_ID);
            statement.setInt(1, Integer.parseInt(referenceId));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching a patient id by " +
                    "refusal reference id", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection in " +
                    "order to fetch a patient id by " +
                    "refusal reference id", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }

        return -1;
    }

    @Override
    public RefusalReference fetchDetailedRefusalReference(String referenceId)
            throws DaoException {
        return DaoFactory.getInstance().getVisitDao()
                .fetchDetailedRefusalReference(referenceId, null);
    }
}

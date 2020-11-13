package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.VisitDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.DepartmentOrigin;
import by.epamtc.jwd.auth.model.user_info.TransportationStatus;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.VisitReason;
import by.epamtc.jwd.auth.model.visit_info.VisitResult;

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
        int visitNumber = Integer.parseInt(visitId);
        lock.lock();

        try {
            conn = pool.takeConnection();
            statements[pointer] = conn.prepareStatement(SqlStatement
                    .SELECT_RESPONSIBLE_DOCTOR);
            statements[pointer].setInt(1, visitNumber);
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
            statements[pointer].setInt(2, visitNumber);
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

    @Override
    public List<AdmissionDepartmentVisit> fetchControlledVisits(AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement[] statements = new PreparedStatement[2];
        List<ResultSet> resultSetList = new ArrayList<>();
        int pointer = 0;

        List<AdmissionDepartmentVisit> controlledVisits = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statements[pointer] = conn.prepareStatement(SqlStatement
                    .SELECT_VISITS_ON_CONTROL_BY_DOCTOR);
            statements[pointer].setInt(1, user.getStaffId());
            ResultSet resultSet = statements[pointer].executeQuery();
            resultSetList.add(resultSet);

            statements[++pointer] = conn.prepareStatement(SqlStatement
                    .SELECT_IF_VISIT_PRESCRIPTION_IS_INCOMPLETE);
            while (resultSet.next()) {
                AdmissionDepartmentVisit controlledVisit = compileShortenedVisit
                        (resultSet);

                boolean isVisitPrescriptionsComplete = true;
                statements[pointer].setInt(1, controlledVisit.getVisitId());
                statements[pointer].setInt(2, controlledVisit.getVisitId());
                ResultSet rSet = statements[pointer].executeQuery();
                if (rSet.next()) {
                    isVisitPrescriptionsComplete = false;
                }
                resultSetList.add(rSet);

                controlledVisit.setPrescriptionsComplete(isVisitPrescriptionsComplete);
                controlledVisits.add(controlledVisit);
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "during fetching visits controlled by AuthUser " +
                    user.toString(), e);
        } catch (SQLException e) {
            throw new DaoException("An error (SQLException) while fetching " +
                    "visits controlled by AuthUser " + user.toString(), e);

        } finally {
            pool.closeConnection(conn, statements, resultSetList);
        }
        return controlledVisits;
    }

    @Override
    public AdmissionDepartmentVisit fetchFullAdmissionDepartmentVisit(String
            visitId) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement.SELECT_FULL_VISIT);
            statement.setInt(1, Integer.parseInt(visitId));
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                AdmissionDepartmentVisit visit = compileShortenedVisit(resultSet);
                return compileFullVisit(visit, resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException("An error while fetching full information" +
                    " about a visit from a source", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "intended for fetching full information about a visit", e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        throw new DaoException("In theory it is unreachable place");
    }

    @Override
    public List<Diagnosis> fetchInnerHospitalDiagnoses(int patientId)
            throws DaoException {
        Connection conn = null;
        PreparedStatement[] statements = new PreparedStatement[2];
        ResultSet[] resultSets = new ResultSet[2];
        int pointer = 0;
        List<Diagnosis> diagnoses = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statements[pointer] = conn.prepareStatement(SqlStatement
                    .SELECT_VISIT_DIAGNOSES);
            resultSets[pointer] = statements[pointer].executeQuery();
            while (resultSets[pointer].next()) {
                Diagnosis diagnosis = compileDiagnosis(resultSets[pointer],
                        DepartmentOrigin.ADMISSION_DEPARTMENT);
                diagnoses.add(diagnosis);
            }

            statements[++pointer] = conn.prepareStatement(SqlStatement.
                    SELECT_INNER_HOSPITAL_DIAGNOSES);
            resultSets[pointer] = statements[pointer].executeQuery();
            while (resultSets[pointer].next()) {
                Diagnosis diagnosis = compileDiagnosis(resultSets[pointer],
                        DepartmentOrigin.INNER_HOSPITAL_DEPARTMENT);
                diagnoses.add(diagnosis);
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking an Connection" +
                    "during fetching of diagnoses of a particular " +
                    "patient.", e);
        } catch (SQLException e) {
            throw new DaoException("An error while fetching all diagnoses of " +
                    "a particular patient.", e);
        } finally {
            pool.closeConnection(conn, statements, resultSets);
        }

        return diagnoses;
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
        String personInfo = compileFullName(firstName, middleName, lastName);
        visit.setPatientShortInfo(personInfo);
        String visitDescription = resultSet.getString(6);
        visit.setPatientVisitDescriptionInfo(visitDescription);
        return visit;
    }

    private String compileFullName(String firstName, String middleName,
            String lastName) {
        return middleName != null
               ? lastName + AppConstant.ONE_WHITESPACE + firstName
                       + AppConstant.ONE_WHITESPACE + middleName
               : lastName + AppConstant.ONE_WHITESPACE + firstName;
    }

    private AdmissionDepartmentVisit compileFullVisit(AdmissionDepartmentVisit
            visit, ResultSet resultSet) throws SQLException {
        int personId = resultSet.getInt(7);
        visit.setPatientId(personId);
        String visitReason = resultSet.getString(8);
        if (visitReason != null) {
            visit.setVisitReason(VisitReason.valueOf(visitReason));
        }
        int doctorId = resultSet.getInt(9);
        visit.setResponsibleDoctorId(doctorId);
        String doctorLastName = resultSet.getString(10);
        String doctorFirstName = resultSet.getString(11);
        String doctorMiddleName = resultSet.getString(12);
        visit.setResponsibleDoctorInfo(compileFullName(doctorFirstName,
                doctorMiddleName, doctorLastName));
        String transStatus = resultSet.getString(13);
        if (transStatus != null) {
            visit.setTransportationStatus(TransportationStatus
                    .valueOf(transStatus));
        }
        int paramedicalStaff = resultSet.getInt(14);
        visit.setResponsibleNonDoctorStaffId(paramedicalStaff);
        String paraLastName = resultSet.getString(15);
        String paraFirstName = resultSet.getString(16);
        String paraMiddleName = resultSet.getString(17);
        visit.setResponsibleNonDoctorStaffInfo(compileFullName(paraFirstName,
                paraMiddleName, paraLastName));
        String complaints = resultSet.getString(18);
        visit.setPatientComplaints(complaints);
        String result = resultSet.getString(19);
        if (result != null) {
            visit.setVisitResult(VisitResult.valueOf(result));
        }
        int hospDepartmentId = resultSet.getInt(20);
        visit.setHospitalizationDepartmentId(hospDepartmentId);
        String hospDepartmentName = resultSet.getString(21);
        visit.setHospitalizationDepartmentInfo(hospDepartmentName);
        return visit;
    }

    private Diagnosis compileDiagnosis(ResultSet resultSet, DepartmentOrigin
            departmentOrigin) throws SQLException {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDepartmentOrigin(departmentOrigin);

        int diagnosisId = resultSet.getInt(1);
        diagnosis.setDiagnosisId(diagnosisId);

        LocalDateTime diagnosisDateTime = null;
        Timestamp timestamp = resultSet.getTimestamp(2);
        if (timestamp != null) {
            diagnosisDateTime = timestamp.toLocalDateTime();
        }
        diagnosis.setDiagnosisDateTime(diagnosisDateTime);

        int diseaseId = resultSet.getInt(3);
        diagnosis.setDiseaseId(diseaseId);

        String diseaseInfo = resultSet.getString(4);
        diagnosis.setDiseaseInfo(diseaseInfo);

        String diagnosisDescription = resultSet.getString(5);
        diagnosis.setDiagnosisDescription(diagnosisDescription);

        int diagnoseDoctorId = resultSet.getInt(6);
        diagnosis.setDiagnoseDoctorId(diagnoseDoctorId);

        String diagnoseDoctorInfo = compileFullName(resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9));
        diagnosis.setDiagnoseDoctorInfo(diagnoseDoctorInfo);

        LocalDateTime cancellationDateTime = null;
        Timestamp cancellationTimestamp = resultSet.getTimestamp(10);
        if (cancellationTimestamp != null) {
            cancellationDateTime = cancellationTimestamp.toLocalDateTime();
        }
        diagnosis.setCancellationDateTime(cancellationDateTime);

        int cancellationDoctorId = resultSet.getInt(11);
        diagnosis.setCancellationDoctorId(cancellationDoctorId);

        String cancellationDoctorInfo = compileFullName(resultSet.getString(12),
                resultSet.getString(13), resultSet.getString(14));
        diagnosis.setCancellationDoctorInfo(cancellationDoctorInfo);

        String cancellationDiagnosisDescription = resultSet.getString(15);
        diagnosis.setCancellationDiagnosisDescription
                (cancellationDiagnosisDescription);

        return diagnosis;
    }
}

package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.VisitDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.dao.util.ProfileRelatedEntitiesCompiler;
import by.epamtc.jwd.auth.dao.util.VisitRelatedEntitiesCompiler;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.med_info.DepartmentOrigin;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.RefusalMedicineRecommendation;
import by.epamtc.jwd.auth.model.visit_info.RefusalReference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultVisitDao implements VisitDao {
    public static ReentrantLock lock = new ReentrantLock();
    private ConnectionPool pool = ConnectionPool.getInstance();
    private VisitRelatedEntitiesCompiler visitRelatedCompiler
            = VisitRelatedEntitiesCompiler.getInstance();
    private ProfileRelatedEntitiesCompiler profileRelatedEntitiesCompiler
            = ProfileRelatedEntitiesCompiler.getInstance();

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
                AdmissionDepartmentVisit visit = visitRelatedCompiler
                        .compileShortenedVisit(resultSet, 1);
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
                AdmissionDepartmentVisit controlledVisit = visitRelatedCompiler
                        .compileShortenedVisit(resultSet, 1);

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
        PreparedStatement[] statements = new PreparedStatement[2];
        ResultSet[] resultSets = new ResultSet[2];
        int pointer = 0;
        int visitIdInt = Integer.parseInt(visitId);

        try {
            conn = pool.takeConnection();
            statements[pointer] = conn.prepareStatement(SqlStatement
                    .SELECT_FULL_VISIT);
            statements[pointer].setInt(1, visitIdInt);
            resultSets[pointer] = statements[pointer].executeQuery();

            if (resultSets[pointer].next()) {
                AdmissionDepartmentVisit visit = visitRelatedCompiler
                        .compileShortenedVisit(resultSets[pointer], 1);

                statements[++pointer] = conn.prepareStatement(SqlStatement
                        .SELECT_IF_VISIT_PRESCRIPTION_IS_INCOMPLETE);
                statements[pointer].setInt(1, visitIdInt);
                statements[pointer].setInt(2, visitIdInt);
                resultSets[pointer] = statements[pointer].executeQuery();

                boolean isVisitPrescriptionsComplete = true;
                if (resultSets[pointer].next()) {
                    isVisitPrescriptionsComplete = false;
                }

                visit.setPrescriptionsComplete(isVisitPrescriptionsComplete);
                return visitRelatedCompiler.compileFullVisit(visit,
                        resultSets[pointer - 1], 7);
            }

        } catch (SQLException e) {
            throw new DaoException("An error while fetching full information" +
                    " about a visit from a source", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "intended for fetching full information about a visit", e);
        } finally {
            pool.closeConnection(conn, statements, resultSets);
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
            statements[pointer].setInt(1, patientId);
            resultSets[pointer] = statements[pointer].executeQuery();
            while (resultSets[pointer].next()) {
                Diagnosis diagnosis = visitRelatedCompiler.compileDiagnosis(
                        resultSets[pointer], DepartmentOrigin.ADMISSION_DEPARTMENT);
                diagnoses.add(diagnosis);
            }

            statements[++pointer] = conn.prepareStatement(SqlStatement.
                    SELECT_INNER_HOSPITAL_DIAGNOSES);
            statements[pointer].setInt(1, patientId);
            resultSets[pointer] = statements[pointer].executeQuery();
            while (resultSets[pointer].next()) {
                Diagnosis diagnosis = visitRelatedCompiler.compileDiagnosis(
                        resultSets[pointer], DepartmentOrigin
                                .INNER_HOSPITAL_DEPARTMENT);
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

    @Override
    public List<MedicinePrescription> fetchVisitMedicinePrescriptions
            (String visitId) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<MedicinePrescription> prescriptionList = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_MEDICINE_PRESCRIPTIONS_BY_VISIT);
            statement.setInt(1, Integer.parseInt(visitId));
            rSet = statement.executeQuery();
            while (rSet.next()) {
                MedicinePrescription prescription = visitRelatedCompiler
                        .compileMedicinePrescription(rSet);
                prescriptionList.add(prescription);
            }

        } catch (SQLException e) {
            throw new DaoException("An error (SQLException) while fetching " +
                    "all medicine prescriptions during a visit. VisitId="
                    + visitId, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error (ConnectionPoolException" +
                    " while taking a connection in order to fetch " +
                    "all medicine prescription during a visit. VisitId="
                    + visitId, e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return prescriptionList;
    }

    @Override
    public List<Prescription> fetchVisitPrescriptions(String visitId)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<Prescription> prescriptionList = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_NON_MEDICINE_PRESCRIPTIONS_BY_VISIT);
            statement.setInt(1, Integer.parseInt(visitId));
            rSet = statement.executeQuery();
            while (rSet.next()) {
                Prescription prescription = visitRelatedCompiler
                        .compileNonMedicinePrescription(rSet);
                prescriptionList.add(prescription);
            }

        } catch (SQLException e) {
            throw new DaoException("An error (SQLException) while fetching " +
                    "all non-medicine prescriptions during a visit. VisitId="
                    + visitId, e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error (ConnectionPoolException" +
                    " while taking a connection in order to fetch " +
                    "all non-medicine prescription during a visit. VisitId="
                    + visitId, e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }


        return prescriptionList;
    }

    @Override
    public boolean changeComplaints(String complaints, String visitId,
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .UPDATE_COMPLAINTS_BY_VISIT);
            statement.setString(1, complaints);
            statement.setInt(2, Integer.parseInt(visitId));
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("An error while changing complaints " +
                    "information during visit", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking connection " +
                    "during changing of patient's complaints", e);
        } finally {
            pool.closeConnection(conn, statement);
        }
    }

    @Override
    public boolean establishDiagnosis(Diagnosis diagnosis, String visitStrId,
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement.INSERT_VISIT_DIAGNOSIS);
            statement.setInt(1, Integer.parseInt(visitStrId));
            statement.setInt(2, Integer.parseInt(diagnosis.getDiseaseInfo()));
            statement.setInt(3, user.getStaffId());
            statement.setString(4, diagnosis.getDiagnosisDescription());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("An error while establishing new diagnosis", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection" +
                    " during establishing new diagnosis", e);
        } finally {
            pool.closeConnection(conn, statement);
        }
    }

    @Override
    public boolean establishMedicinePrescription(MedicinePrescription
            prescription, AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement[] statements = new PreparedStatement[3];
        ResultSet[] resultSets = new ResultSet[3];
        int pointer = 0;


        try {
            conn = pool.takeConnection();
            statements[pointer] = conn.prepareStatement(SqlStatement
                    .SELECT_PATIENT_ID_BY_VISIT_ID);
            statements[pointer].setInt(1, prescription.getOriginDocumentId());
            resultSets[pointer] = statements[pointer].executeQuery();
            int personId = 0;
            if (resultSets[pointer].next()) {
                personId = resultSets[pointer].getInt(1);
            }


            statements[++pointer] = conn.prepareStatement(SqlStatement
                    .SELECT_IF_THERE_IS_ALLERGY);
            statements[pointer].setInt(1, prescription.getMedicineId());
            statements[pointer].setInt(2, personId);
            resultSets[pointer] = statements[pointer].executeQuery();
            if (resultSets[pointer].next()) {
                return false;
            }


            statements[++pointer] = conn.prepareStatement(SqlStatement
                    .INSERT_VISIT_MEDICINE_PRESCRIPTION);
            statements[pointer].setInt(1, prescription.getOriginDocumentId());
            statements[pointer].setInt(2, prescription.getMedicineId());
            statements[pointer].setTimestamp(3, Timestamp.valueOf
                    (prescription.getTargetApplicationDateTime()));
            statements[pointer].setDouble(4, prescription.getDosageQuantity());
            statements[pointer].setInt(5, prescription.getDosageMeasureUnit()
                    .getMeasureUnitId());
            statements[pointer].setInt(6, user.getStaffId());
            return statements[pointer].executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("An error while establishing new medicine" +
                    " prescription.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection" +
                    " during establishing new medicine prescription.", e);
        } finally {
            pool.closeConnection(conn, statements, resultSets);
        }
    }

    @Override
    public boolean establishPrescription(Prescription prescription,
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .INSERT_VISIT_PRESCRIPTION);
            statement.setInt(1, prescription.getOriginDocumentId());
            statement.setInt(2, user.getStaffId());
            statement.setString(3, prescription.getPrescriptionDescription());
            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection during " +
                    "establishment of a prescription.\n"
                    + prescription.toString(), e);
        } catch (SQLException e) {
            throw new DaoException("An error while establishment of a " +
                    "prescription.\n" + prescription.toString(), e);
        } finally {
            pool.closeConnection(conn, statement);
        }
        return true;
    }

    @Override
    public boolean cancelPrescription(String prescriptionId, AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .UPDATE_CANCEL_PRESCRIPTION);
            statement.setInt(1, user.getStaffId());
            statement.setInt(2, Integer.parseInt(prescriptionId));
            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection during " +
                    "cancellation of a non-medicine prescription. " +
                    "PrescriptionId = " + prescriptionId, e);
        } catch (SQLException e) {
            throw new DaoException("An error while cancelling a non-medicine " +
                    "prescription. PrescriptionId = " + prescriptionId, e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }

    @Override
    public boolean cancelMedicinePrescription(String medPrescriptionId,
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .UPDATE_CANCEL_MED_PRESCRIPTION);
            statement.setInt(1, user.getStaffId());
            statement.setInt(2, Integer.parseInt(medPrescriptionId));
            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection during " +
                    "cancellation of a medicine prescription. " +
                    "MedPrescriptionId = " + medPrescriptionId, e);
        } catch (SQLException e) {
            throw new DaoException("An error while cancelling a medicine " +
                    "prescription. MedPrescriptionId = " + medPrescriptionId, e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }

    @Override
    public List<MedicinePrescription> fetchAllNewMedicinePrescriptions()
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<MedicinePrescription> prescriptions = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_MEDICINE_UNEXECUTED_PRESCRIPTIONS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MedicinePrescription prescription = visitRelatedCompiler
                        .compileMedicinePrescription(resultSet);
                visitRelatedCompiler.updateMedPrescriptionWithPatientInfo(
                        prescription, resultSet);
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            throw new DaoException("An error (SQLException) while fetching " +
                    "all unexecuted medicine prescriptions", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error (ConnectionPoolException" +
                    " while taking a connection in order to fetch " +
                    "all unexecuted medicine prescriptions", e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return prescriptions;
    }

    @Override
    public MedicinePrescription fetchVisitMedPrescriptionById(
            String medPrescriptionId) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        MedicinePrescription prescription = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_MED_PRESCRIPTION_BY_ID);
            statement.setInt(1, Integer.parseInt(medPrescriptionId));
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                prescription = visitRelatedCompiler.compileMedicinePrescription(
                        resultSet);
                visitRelatedCompiler.updateMedPrescriptionWithPatientInfo(
                        prescription, resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching a visit medicine " +
                    "prescription by visit id from a db", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "during fetching of a visit medicine prescription by " +
                    "visit id", e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return prescription;
    }

    @Override
    public boolean executeMedPrescription(String medPrescriptionId,
            String executionResult, AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .UPDATE_EXECUTE_MED_PRESCRIPTION_BY_ID);
            statement.setString(1, executionResult);
            statement.setInt(2, user.getStaffId());
            statement.setInt(3, Integer.parseInt(medPrescriptionId));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while placing information about " +
                    "execution of a medicine prescriptions.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection during " +
                    "placing information about execution of a medicine " +
                    "prescriptions.", e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }

    @Override
    public List<Prescription> fetchAllNewNonMedicinePrescriptions()
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Prescription> prescriptions = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_NON_MEDICINE_UNEXECUTED_PRESCRIPTIONS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Prescription prescription = visitRelatedCompiler
                        .compileNonMedicinePrescription(resultSet);
                visitRelatedCompiler.updateNonMedPrescriptionWithPatientInfo
                        (prescription, resultSet);
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            throw new DaoException("An error (SQLException) while fetching " +
                    "all unexecuted non medicine prescriptions", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error (ConnectionPoolException" +
                    " while taking a connection in order to fetch " +
                    "all unexecuted non medicine prescriptions", e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return prescriptions;
    }

    @Override
    public boolean acceptPrescriptionOnControl(String prescriptionId,
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .UPDATE_CONTROL_PRESCRIPTION);
            statement.setInt(1, user.getStaffId());
            statement.setInt(2, Integer.parseInt(prescriptionId));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while accepting a non-medicine " +
                    "prescription on control", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "while accepting a non-medicine prescription on control", e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }

    @Override
    public List<Prescription> fetchControlledVisitPrescriptions(AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Prescription> prescriptions = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_CONTROLLED_NON_MEDICINE_UNEXECUTED_PRESCRIPTIONS);
            statement.setInt(1, user.getStaffId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Prescription prescription = visitRelatedCompiler
                        .compileNonMedicinePrescription(resultSet);
                visitRelatedCompiler.updateNonMedPrescriptionWithPatientInfo(
                        prescription, resultSet);
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            throw new DaoException("An error (SQLException) while fetching " +
                    "all controlled unexecuted non medicine prescriptions", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error (ConnectionPoolException" +
                    " while taking a connection in order to fetch " +
                    "all controlled unexecuted non medicine prescriptions", e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return prescriptions;
    }

    @Override
    public Prescription fetchVisitPrescriptionById(String prescriptionId)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Prescription prescription = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_NON_MED_PRESCRIPTION_BY_ID);
            statement.setInt(1, Integer.parseInt(prescriptionId));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                prescription = visitRelatedCompiler
                        .compileNonMedicinePrescription(resultSet);
                visitRelatedCompiler.updateNonMedPrescriptionWithPatientInfo(
                        prescription, resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching a non-medicine " +
                    "prescription by its id from the source.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "during fetching a non-medicine prescription by its id", e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return prescription;
    }

    @Override
    public boolean executePrescription(String prescriptionId,
            String executionResult, AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .UPDATE_EXECUTE_NON_MED_PRESCRIPTION_BY_ID);
            statement.setString(1, executionResult);
            statement.setInt(2, user.getStaffId());
            statement.setInt(3, Integer.parseInt(prescriptionId));
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException("An error while placing information about " +
                    "execution of a non-medicine prescriptions.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection during " +
                    "placing information about execution of a non-medicine " +
                    "prescriptions.", e);
        } finally {
            pool.closeConnection(conn, statement);
        }
    }

    @Override
    public boolean startRefusalProcedure(String visitId, AuthUser user)
            throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .UPDATE_START_REFUSAL_PROCEDURE);
            statement.setInt(1, Integer.parseInt(visitId));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while starting a refusal " +
                    "procedure.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "in order to start a refusal procedure.", e);
        } finally {
            pool.closeConnection(connection, statement);
        }

        return true;
    }

    @Override
    public List<RefusalMedicineRecommendation> fetchRefusalMedicineRecommendations(
            String visitId, AuthUser user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<RefusalMedicineRecommendation> recommendations = new ArrayList<>();

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .SELECT_REFUSAL_MEDICINE_RECOMMENDATIONS);
            statement.setInt(1, Integer.parseInt(visitId));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                RefusalMedicineRecommendation recom = visitRelatedCompiler
                        .compileRefusalMedicineRecommendation(resultSet);
                recommendations.add(recom);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching refusal medicine " +
                    " recommendations by visit id.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection in " +
                    "order to fetch refusal medicine recommendations by " +
                    "visit id.", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }

        return recommendations;
    }

    @Override
    public List<AdmissionDepartmentVisit> fetchVisitToRefuse(AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<AdmissionDepartmentVisit> visits = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_VISITS_TO_REFUSE_BY_DOCTOR);
            statement.setInt(1, user.getStaffId());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                AdmissionDepartmentVisit visit = visitRelatedCompiler
                        .compileShortenedVisit(resultSet, 1);
                visits.add(visit);
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "during fetching visits to refuse by AuthUser " +
                    user.toString(), e);
        } catch (SQLException e) {
            throw new DaoException("An error (SQLException) while fetching " +
                    "visits to refuse by AuthUser " + user.toString(), e);

        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return visits;
    }

    @Override
    public boolean addRefusalMedicineRecommendation(String medicineId,
            String visitId, String intakeInstruction, AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .UPDATE_ADD_REFUSAL_MEDICINE_RECOMMENDATION);
            statement.setInt(1, Integer.parseInt(visitId));
            statement.setInt(2, user.getStaffId());
            statement.setInt(3, Integer.parseInt(medicineId));
            statement.setString(4, intakeInstruction);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("An error while adding a refusal medicine " +
                    "recommendation", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection in " +
                    "order to add a refusal medicine recommendation.", e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }

    @Override
    public boolean cancelRefusalMedicineRecommendation(String recommendId,
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .DELETE_CANCEL_REFUSAL_MEDICINE_RECOMMENDATION);
            statement.setInt(1, Integer.parseInt(recommendId));
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("An error while cancelling a refusal " +
                    "medicine recommendation", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection in " +
                    "order to cancel a refusal medicine recommendation.", e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }

    @Override
    public int formRefusalReference(String refusalRecommendations,
            String visitId, AuthUser user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int referenceId = 0;

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .INSERT_REFUSAL_REFERENCE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, Integer.parseInt(visitId));
            statement.setInt(2, user.getStaffId());
            statement.setString(3, refusalRecommendations);
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                referenceId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while forming a refusal " +
                    "reference if a patient is not hospitalized.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while takina  connection in " +
                    "order to form a refusal reference if a patient " +
                    "is not hospitalized.", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }

        return referenceId;
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
                    .SELECT_REFUSAL_REFERENCES_BY_DOCTOR_ID);
            statement.setInt(1, user.getStaffId());
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

                AdmissionDepartmentVisit visitInfo = visitRelatedCompiler
                        .compileShortenedVisit(resultSet, 3);
                reference.setVisitInfo(visitInfo);

                references.add(reference);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching all refusal " +
                    "references.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "in order to fetch all refusal references.", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }

        return references;
    }

    @Override
    public RefusalReference fetchDetailedRefusalReference(String referenceId,
            AuthUser user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        RefusalReference reference = null;

        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SqlStatement
                    .SELECT_REFUSAL_REFERENCE_BY_ID);
            statement.setInt(1, Integer.parseInt(referenceId));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reference = new RefusalReference();
                int refusalReferenceId = resultSet.getInt(1);

                LocalDateTime referenceDatetime = null;
                Timestamp referenceTimestamp = resultSet.getTimestamp(2);
                if (referenceTimestamp != null) {
                    referenceDatetime = referenceTimestamp.toLocalDateTime();
                }

                String refusalRecommendations = resultSet.getString(3);

                PatientInfo patientInfo = profileRelatedEntitiesCompiler
                        .compilePatientInfo(resultSet, 4);

                AdmissionDepartmentVisit visit = visitRelatedCompiler
                        .compileShortenedVisit(resultSet, 51);
                visitRelatedCompiler.compileFullVisit(visit, resultSet, 57);

                List<Diagnosis> diagnoses;
                List<MedicinePrescription> medPrescriptions;
                List<Prescription> prescriptions;
                List<RefusalMedicineRecommendation> refusalMedicineRecommendations;
            }

        } catch (SQLException e) {
            throw new DaoException("An error while fetching a refusal " +
                    "reference in detail.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection " +
                    "in order to fetch a refusal reference in detail.", e);
        } finally {
            pool.closeConnection(connection, statement, resultSet);
        }

        return reference;
    }
}

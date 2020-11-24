package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.ProfileDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.dao.util.ProfileRelatedEntitiesCompiler;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.user_info.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DefaultProfileDao implements ProfileDao {
    private ConnectionPool pool = ConnectionPool.getInstance();
    ProfileRelatedEntitiesCompiler profileRelatedCompiler
            = ProfileRelatedEntitiesCompiler.getInstance();

    @Override
    public PatientInfo fetchPatientInfo(AuthUser authUser) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        PatientInfo patientInfo = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement.SELECT_PATIENT_INFO);
            statement.setInt(1, authUser.getUserId());
            rSet = statement.executeQuery();
            if (rSet.next()) {
                patientInfo = profileRelatedCompiler.compilePatientInfo(rSet, 1);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching data from DB " +
                    "(PatientInfo)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during fetching of patient info", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        if (patientInfo == null) {
            throw new DaoException(String.format("Fetching of patient " +
                    "information met an impossible execution outcome: auth " +
                    "user information: \"%s\"", authUser));
        }

        return patientInfo;
    }

    @Override
    public boolean changePatientInfo(PatientInfo changingInfo,
            AuthUser user) throws DaoException {
        Connection conn = null;
        ArrayList<PreparedStatement> statements = new ArrayList<>(12);

        try {
            conn = pool.takeConnection();
            conn.setAutoCommit(false);

            int patientId = user.getUserId();
            String phoneNumber = changingInfo.getPhoneNumber();
            if (phoneNumber != null) {
                changePatientPhoneNumber(phoneNumber, patientId, conn,
                        statements);
            }

            MaritalStatus maritalStatus = changingInfo.getMaritalStatus();
            if (maritalStatus != null) {
                changeMaritalStatus(maritalStatus, patientId, conn, statements);
            }

            IdentityDocument idDocument = changingInfo.getIdentityDocument();
            if (idDocument != null) {
                changeIdentityDocument(idDocument, patientId, conn, statements);
            }

            Address address = changingInfo.getHomeAddress();
            if (address != null) {
                changeAddress(address, patientId, conn, statements);
            }

            String emergencyContactPersonInfo = changingInfo
                    .getInCaseOfEmergencyContactPersonInfo();
            if (emergencyContactPersonInfo != null) {
                changeEmergencyCaseContactPersonInfo(emergencyContactPersonInfo,
                        patientId, conn, statements);
            }

            String emergencyContactPersonPhone = changingInfo
                    .getInCaseOfEmergencyContactPersonPhone();
            if (emergencyContactPersonPhone != null) {
                changeEmergencyContactPersonPhone(emergencyContactPersonPhone,
                        patientId, conn, statements);
            }

            BloodType bloodType = changingInfo.getBloodType();
            if (bloodType != null) {
                changeBloodType(bloodType, patientId, conn, statements);
            }

            RhBloodGroup rhBloodGroup = changingInfo.getRhBloodGroup();
            if (rhBloodGroup != null) {
                changeRhBloodGroup(rhBloodGroup, patientId, conn, statements);
            }

            DisabilityDegree disabilityDegree = changingInfo.getDisabilityDegree();
            if (disabilityDegree != null) {
                changeDisabilityDegree(disabilityDegree, patientId, conn,
                        statements);
            }

            TransportationStatus transportationStatus = changingInfo
                    .getTransportationStatus();
            if (transportationStatus != null) {
                changeTransportationStatus(transportationStatus, patientId,
                        conn, statements);
            }

            conn.commit();

        } catch (ConnectionPoolException e) {
            //noinspection ConstantConditions
            pool.rollBackTransaction(conn);
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during changing of patient info", e);
        } catch (SQLException e) {
            pool.rollBackTransaction(conn);
            throw new DaoException("An error while changing data from DB " +
                    "(patient info)", e);
        } finally {
            pool.closeConnection(conn, statements);
        }

        return true;
    }

    @Override
    public List<MedicalHistoryPermission> fetchMedicalHistoryPermissions(AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<MedicalHistoryPermission> medicalHistoryPermissions = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_MEDICAL_HISTORY_PERMISSIONS);
            statement.setInt(1, user.getUserId());
            rSet = statement.executeQuery();
            while (rSet.next()) {
                MedicalHistoryPermission record = profileRelatedCompiler
                        .compileMedicalHistoryPermission(rSet);
                medicalHistoryPermissions.add(record);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching data from DB " +
                    "(MedicalHistoryPermission records)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during fetching of " +
                    "MedicalHistoryPermission", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return medicalHistoryPermissions;
    }

    @Override
    public boolean cancelMedicalHistoryPermission(String permissionId,
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .CANCEL_MEDICAL_HISTORY_PERMISSION);
            statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(2, Integer.parseInt(permissionId));
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("An error while deleting data from DB " +
                    "(MedicalHistoryPermission records)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during deleting of " +
                    "MedicalHistoryPermission", e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }

    @Override
    public boolean addMedicalHistoryPermission(String recipientId,
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .INSERT_MEDICAL_HISTORY_PERMISSION);
            statement.setInt(1, user.getUserId());
            statement.setInt(2, Integer.parseInt(recipientId));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while addition data into DB " +
                    "(MedicalHistoryPermission records)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during addition of " +
                    "MedicalHistoryPermission", e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }

    @Override
    public AllergicReactionsInfo fetchAllergicReactionsInfo(AuthUser user)
            throws DaoException {
        Connection conn = null;
        PreparedStatement[] statements = new PreparedStatement[2];
        ResultSet[] rSet = new ResultSet[2];
        int pointer = 0;

        AllergicReactionsInfo allergicReactions = new AllergicReactionsInfo();

        try {
            conn = pool.takeConnection();
            statements[pointer] = conn.prepareStatement(SqlStatement
                    .SELECT_ALLERGIC_FOOD_REACTIONS);
            statements[pointer].setInt(1, user.getUserId());

            rSet[pointer] = statements[pointer].executeQuery();
            updateWithAllergicFoodReaction(allergicReactions, rSet[pointer]);

            statements[++pointer] = conn.prepareStatement(SqlStatement
                    .SELECT_ALLERGIC_MEDICINE_REACTION);
            statements[pointer].setInt(1, user.getUserId());

            rSet[pointer] = statements[pointer].executeQuery();
            updateWithAllergicMedicineReaction(allergicReactions, rSet[pointer]);

        } catch (SQLException e) {
            throw new DaoException("An error while fetching data from DB " +
                    "(AllergicReactionsInfo)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during fetching of " +
                    "AllergicReactionsInfo", e);
        } finally {
            pool.closeConnection(conn, statements, rSet);
        }

        return allergicReactions;
    }

    @Override
    public boolean addAllergicFoodReaction(AllergicFoodReaction reaction,
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .INSERT_ALLERGIC_FOOD_REACTION);
            statement.setInt(1, user.getUserId());
            statement.setInt(2, Integer.parseInt(reaction
                    .getFoodTypeInfo()));
            statement.setObject(3, reaction.getDetectionDate());
            statement.setString(4, reaction.getAllergicDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while addition data into DB " +
                    "(AllergicFoodReaction records)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during addition of " +
                    "AllergicFoodReaction", e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }

    @Override
    public boolean addAllergicMedicineReaction(AllergicMedicineReaction reaction,
            AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .INSERT_ALLERGIC_MEDICINE_REACTION);
            statement.setInt(1, user.getUserId());
            statement.setInt(2, Integer.parseInt(reaction
                    .getMedicineDescription()));
            statement.setObject(3, reaction.getDetectionDate());
            statement.setString(4, reaction.getAllergicReaction());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while addition data into DB " +
                    "(AllergicMedicineReaction records)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during addition of " +
                    "AllergicMedicineReaction", e);
        } finally {
            pool.closeConnection(conn, statement);
        }

        return true;
    }

    @Override
    public List<ExtremelyHazardousDiseaseCase>
    fetchCasesOfExtremelyHazardousDiseases(AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;

        List<ExtremelyHazardousDiseaseCase> cases = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement
                    .SELECT_EXTREMELY_HAZARDOUS_DISEASE_CASE);
            statement.setInt(1, user.getUserId());
            rSet = statement.executeQuery();
            while (rSet.next()) {
                ExtremelyHazardousDiseaseCase dCase = profileRelatedCompiler
                        .compileExtremelyHazardousDiseaseCase(rSet);
                cases.add(dCase);
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching data from DB " +
                    "(ExtremelyHazardousDiseaseCase records)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during fetching of " +
                    "ExtremelyHazardousDiseaseCase", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }
        return cases;
    }

    @Override
    public boolean addExtremelyHazardousDisease(ExtremelyHazardousDiseaseCase
            disease, AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement[] statements = new PreparedStatement[3];
        ResultSet resultSet = null;
        int pointer = 0;

        try {
            conn = pool.takeConnection();
            conn.setAutoCommit(false);
            statements[pointer] = conn.prepareStatement(SqlStatement.SELECT_HAZARDOUS_STATUS);
            statements[pointer].setInt(1, user.getUserId());

            boolean isHazardousBefore = false;
            resultSet = statements[pointer].executeQuery();
            if (resultSet.next()) {
                isHazardousBefore = resultSet.getBoolean(1);
            }

            if (!isHazardousBefore) {
                statements[++pointer] = conn.prepareStatement(SqlStatement
                        .INSERT_HAZARDOUS_FLAG_TO_PERSON);
                statements[pointer].setInt(1, user.getUserId());
                statements[pointer].executeUpdate();
            }

            statements[++pointer] = conn.prepareStatement(SqlStatement.ADD_HAZARDOUS_CASE);
            statements[pointer].setInt(1, Integer.parseInt(disease
                    .getDiseaseDescription()));
            statements[pointer].setString(2, disease.getCaseDescription());
            statements[pointer].setObject(3, disease.getDetectionDate());
            statements[pointer].setObject(4, user.getUserId());

            statements[pointer].executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            pool.rollBackTransaction(conn);
            throw new DaoException("An error while adding extremely " +
                    "hazardous disease", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while adding extremely " +
                    "hazardous disease in connection pool", e);
        } finally {
            pool.closeConnection(conn, statements, resultSet);
        }


        return true;
    }

    private void changePatientPhoneNumber(String phoneNumber, int patientId,
            Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement phoneNumberInsertStatement = conn.prepareStatement(
                SqlStatement.UPDATE_PHONE_NUMBER);
        statements.add(phoneNumberInsertStatement);

        phoneNumberInsertStatement.setString(1, phoneNumber);
        phoneNumberInsertStatement.setInt(2, patientId);

        phoneNumberInsertStatement.executeUpdate();
    }

    private void changeMaritalStatus(MaritalStatus maritalStatus, int patientId,
            Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement mStatusUpdateStatement = conn.prepareStatement(
                SqlStatement.UPDATE_MARITAL_STATUS);
        statements.add(mStatusUpdateStatement);

        mStatusUpdateStatement.setString(1, maritalStatus.name());
        mStatusUpdateStatement.setInt(2, patientId);

        mStatusUpdateStatement.executeUpdate();
    }

    private void changeIdentityDocument(IdentityDocument idDocument, int patientId,
            Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement idDocInsertStatement = conn.prepareStatement(
                SqlStatement.INSERT_IDENTITY_DOCUMENT, Statement.RETURN_GENERATED_KEYS);
        statements.add(idDocInsertStatement);

        idDocInsertStatement.setInt(1, patientId);
        IdentificationDocumentType idType = idDocument.getIdentificationDocumentType();
        idDocInsertStatement.setInt(2, idType.getTypeId());
        idDocInsertStatement.setString(3, idDocument.getSeries());
        idDocInsertStatement.setInt(4, idDocument.getDocumentNumber());
        idDocInsertStatement.setString(5, idDocument.getLatinHolderName());
        idDocInsertStatement.setString(6, idDocument.getLatinHolderSurName());
        int citizenshipId = Integer.parseInt(idDocument.getCitizenShip());
        idDocInsertStatement.setInt(7, citizenshipId);
        idDocInsertStatement.setObject(8, idDocument.getBirthday());
        idDocInsertStatement.setString(9, idDocument.getPersonalNumber());
        Gender gender = idDocument.getGender();
        idDocInsertStatement.setString(10, gender.name());
        idDocInsertStatement.setString(11, idDocument.getPlaceOfOrigin());
        idDocInsertStatement.setObject(12, idDocument.getDateOfIssue());
        idDocInsertStatement.setObject(13, idDocument.getDateOfExpiry());
        idDocInsertStatement.setString(14, idDocument.getIssueAuthority());

        idDocInsertStatement.executeUpdate();

        int idOfIdentityDocument = receiveGeneratedKeyAfterStatementExecution
                (idDocInsertStatement);

        PreparedStatement updatePatientWithIdDoc = conn.prepareStatement(
                SqlStatement.UPDATE_IDENTITY_DOCUMENT);
        statements.add(updatePatientWithIdDoc);

        updatePatientWithIdDoc.setInt(1, idOfIdentityDocument);
        updatePatientWithIdDoc.setInt(2, patientId);

        updatePatientWithIdDoc.executeUpdate();
    }

    private void changeAddress(Address address, int patientId, Connection conn,
            ArrayList<PreparedStatement> statements) throws SQLException {
        PreparedStatement addressInsertStatement = conn.prepareStatement(
                SqlStatement.INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);
        statements.add(addressInsertStatement);

        addressInsertStatement.setString(1, address.getZipCode());
        int road_id = Integer.parseInt(address.getRoad());
        addressInsertStatement.setInt(2, road_id);
        addressInsertStatement.setString(3, address.getHouse());
        addressInsertStatement.setString(4, address.getBuilding());
        addressInsertStatement.setString(5, address.getRoom());

        addressInsertStatement.executeUpdate();
        int addressId = receiveGeneratedKeyAfterStatementExecution(addressInsertStatement);

        PreparedStatement updatePatientWithNewAddress = conn.prepareStatement(
                SqlStatement.UPDATE_ADDRESS);
        statements.add(updatePatientWithNewAddress);

        updatePatientWithNewAddress.setInt(1, addressId);
        updatePatientWithNewAddress.setInt(2, patientId);

        updatePatientWithNewAddress.executeUpdate();
    }

    private void changeEmergencyCaseContactPersonInfo(String emergencyContactPersonInfo,
            int patientId, Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement updatePatientWithEmergencyPersonInfo = conn
                .prepareStatement(SqlStatement.UPDATE_EMERGENCY_PERSON);
        statements.add(updatePatientWithEmergencyPersonInfo);

        updatePatientWithEmergencyPersonInfo.setString(1,
                emergencyContactPersonInfo);
        updatePatientWithEmergencyPersonInfo.setInt(2, patientId);

        updatePatientWithEmergencyPersonInfo.executeUpdate();
    }

    private void changeEmergencyContactPersonPhone(String emergencyContactPhone,
            int patientId, Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement updatePatientWithEmergencyPersonPhone = conn
                .prepareStatement(SqlStatement.UPDATE_EMERGENCY_PHONE);
        statements.add(updatePatientWithEmergencyPersonPhone);

        updatePatientWithEmergencyPersonPhone.setString(1,
                emergencyContactPhone);
        updatePatientWithEmergencyPersonPhone.setInt(2, patientId);

        updatePatientWithEmergencyPersonPhone.executeUpdate();
    }

    private void changeBloodType(BloodType bloodType, int patientId,
            Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement updatePatientWithBloodType = conn.prepareStatement(
                SqlStatement.UPDATE_BLOOD_TYPE);
        statements.add(updatePatientWithBloodType);

        updatePatientWithBloodType.setString(1, bloodType != BloodType.UNKNOWN
                                                ? bloodType.name()
                                                : null);
        updatePatientWithBloodType.setInt(2, patientId);

        updatePatientWithBloodType.executeUpdate();
    }

    private void changeRhBloodGroup(RhBloodGroup rhBloodGroup, int patientId,
            Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement updatePatientWithRhGroup = conn.prepareStatement(
                SqlStatement.UPDATE_RH_BLOOD_GROUP);
        statements.add(updatePatientWithRhGroup);

        updatePatientWithRhGroup.setString(1, rhBloodGroup != RhBloodGroup.UNKNOWN
                                              ? rhBloodGroup.name()
                                              : null);
        updatePatientWithRhGroup.setInt(2, patientId);

        updatePatientWithRhGroup.executeUpdate();
    }

    private void changeDisabilityDegree(DisabilityDegree disabilityDegree,
            int patientId, Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement updatePatientWithDisability = conn.prepareStatement(
                SqlStatement.UPDATE_DISABILITY_DEGREE);
        statements.add(updatePatientWithDisability);

        updatePatientWithDisability.setString(1, disabilityDegree
                .name());
        updatePatientWithDisability.setInt(2, patientId);

        updatePatientWithDisability.executeUpdate();
    }

    private void changeTransportationStatus(TransportationStatus status,
            int patientId, Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement updateTransportationStatus = conn.prepareStatement(
                SqlStatement.UPDATE_TRANSPORTATION_STATUS);
        statements.add(updateTransportationStatus);

        updateTransportationStatus.setString(1, status.name());
        updateTransportationStatus.setInt(2, patientId);

        updateTransportationStatus.executeUpdate();
    }

    private void updateWithAllergicFoodReaction(AllergicReactionsInfo
            allergicReactions, ResultSet rSet) throws SQLException {
        List<AllergicFoodReaction> food = new ArrayList<>();
        allergicReactions.setAllergicFoodReactions(food);
        while (rSet.next()) {
            int reactionId = rSet.getInt(1);
            int foodTypeId = rSet.getInt(2);
            String foodTypeDescription = rSet.getString(3);
            Date date = rSet.getDate(4);
            LocalDate detectionDate = date != null ? date.toLocalDate()
                                                   : null;
            String allergicReactionDescription = rSet.getString(5);
            food.add(new AllergicFoodReaction(reactionId, foodTypeId,
                    foodTypeDescription, detectionDate, allergicReactionDescription));
        }
    }

    private void updateWithAllergicMedicineReaction(AllergicReactionsInfo
            allergicReactions, ResultSet resultSet) throws SQLException {
        List<AllergicMedicineReaction> medicineReactions = new ArrayList<>();
        allergicReactions.setAllergicMedicineReactions(medicineReactions);

        while (resultSet.next()) {
            int reactionId = resultSet.getInt(1);
            int medicineId = resultSet.getInt(2);
            String medicineDescription = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            LocalDate detectionDate = date != null ? date.toLocalDate()
                                                   : null;
            String allergicReaction = resultSet.getString(5);
            medicineReactions.add(new AllergicMedicineReaction(reactionId,
                    medicineId, medicineDescription, detectionDate,
                    allergicReaction));
        }
    }

    private int receiveGeneratedKeyAfterStatementExecution(PreparedStatement
            idDocInsertStatement) throws SQLException {
        int generatedId = 0;
        ResultSet generatedKeys = idDocInsertStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            generatedId = generatedKeys.getInt(1);
        }
        return generatedId;
    }
}

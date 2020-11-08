package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.ProfileDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.user_info.Address;
import by.epamtc.jwd.auth.model.user_info.BloodType;
import by.epamtc.jwd.auth.model.user_info.DisabilityDegree;
import by.epamtc.jwd.auth.model.user_info.Gender;
import by.epamtc.jwd.auth.model.user_info.IdentificationDocumentType;
import by.epamtc.jwd.auth.model.user_info.IdentityDocument;
import by.epamtc.jwd.auth.model.user_info.MaritalStatus;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.model.user_info.RhBloodGroup;
import by.epamtc.jwd.auth.model.user_info.TransportationStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class DefaultProfileDao implements ProfileDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

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
                patientInfo = compilePatientInfo(rSet);
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

    private PatientInfo compilePatientInfo(ResultSet rSet) throws SQLException {
        String photoPath = rSet.getString(1);
        String firstName = rSet.getString(2);
        String middleName = rSet.getString(3);
        String lastName = rSet.getString(4);
        LocalDate birthday = rSet.getDate(5).toLocalDate();
        Gender gender = null;
        String genderString = rSet.getString(6);
        if (genderString != null) {
            gender = Gender.valueOf(genderString);
        }

        String email = rSet.getString(7);
        String phoneNumber = rSet.getString(8);

        MaritalStatus maritalStatus = null;
        String maritalStatusString = rSet.getString(9);
        if (maritalStatusString != null) {
            maritalStatus = MaritalStatus.valueOf(maritalStatusString);
        }

        IdentityDocument identityDocument = compileIdentityDocument(rSet);
        Address address = compileAddress(rSet);

        String inCaseOfEmergencyPerson = rSet.getString(39);
        String inCaseOfEmergencyPhone = rSet.getString(40);

        BloodType bloodType = null;
        String bloodTypeString = rSet.getString(41);
        if (bloodTypeString != null) {
            bloodType = BloodType.valueOf(bloodTypeString);
        }

        RhBloodGroup rhBlood = null;
        String rhBloodString = rSet.getString(42);
        if (rhBloodString != null) {
            rhBlood = RhBloodGroup.valueOf(rhBloodString);
        }

        DisabilityDegree disabilityDegree = null;
        String disabilityDegreeString = rSet.getString(43);
        if (disabilityDegreeString != null) {
            disabilityDegree = DisabilityDegree.valueOf(disabilityDegreeString);
        }

        TransportationStatus transStatus = null;
        String transStatusString = rSet.getString(44);
        if (transStatusString != null) {
            transStatus = TransportationStatus.valueOf(transStatusString);
        }

        boolean hasAllergyToFood = rSet.getBoolean(45);
        boolean hasAllergyToMedicine = rSet.getBoolean(46);
        boolean hasExtremelyHazardousDiseases = rSet.getBoolean(47);

        return new PatientInfo(photoPath, firstName, middleName, lastName,
                birthday, gender, email, phoneNumber, maritalStatus,
                identityDocument, address, inCaseOfEmergencyPerson,
                inCaseOfEmergencyPhone, bloodType, rhBlood, disabilityDegree,
                transStatus, (hasAllergyToFood || hasAllergyToMedicine),
                hasExtremelyHazardousDiseases);
    }

    private IdentityDocument compileIdentityDocument(ResultSet rSet) throws SQLException {
        int identityDocId = rSet.getInt(10);
        if (identityDocId > 0) {
            int identityDocTypeId = rSet.getInt(11);
            IdentificationDocumentType identityDocType = null;
            for (IdentificationDocumentType docType
                    : IdentificationDocumentType.values()) {
                if (docType.getTypeId() == identityDocTypeId) {
                    identityDocType = docType;
                    break;
                }
            }
            if (identityDocType == null) {
                identityDocType = IdentificationDocumentType.PASSPORT;
            }
            identityDocType.setDescription(rSet.getString(12));
            String series = rSet.getString(13);
            int serialNumberOfDocument = rSet.getInt(14);
            String latinHolderName = rSet.getString(15);
            String latinHolderSurname = rSet.getString(16);
            String idDocCountryName = rSet.getString(17);
            LocalDate idDocBirthday = rSet.getDate(18).toLocalDate();
            String personalNumber = rSet.getString(19);
            Gender idDocGender = Gender.valueOf(rSet.getString(20));
            String placeOfOrigin = rSet.getString(21);
            LocalDate dateOfIssue = rSet.getDate(22).toLocalDate();
            LocalDate dateOfExpiry = rSet.getDate(23).toLocalDate();
            String issueAuthority = rSet.getString(24);
            return new IdentityDocument(identityDocId, identityDocType, series,
                    serialNumberOfDocument, latinHolderName, latinHolderSurname,
                    idDocCountryName, idDocBirthday, personalNumber,
                    idDocGender, placeOfOrigin, dateOfIssue, dateOfExpiry,
                    issueAuthority);
        }
        return null;
    }

    private Address compileAddress(ResultSet rSet) throws SQLException {
        int addressId = rSet.getInt(25);
        if (addressId > 0) {
            String zipCode = rSet.getString(26);
            String shortCountryName = rSet.getString(27);
            String regionName = rSet.getString(28)
                    + AppConstant.ONE_WHITESPACE
                    + rSet.getString(29);
            String areaName = rSet.getString(30)
                    + AppConstant.ONE_WHITESPACE
                    + rSet.getString(31);
            String settleName = rSet.getString(32)
                    + AppConstant.ONE_WHITESPACE
                    + rSet.getString(33);
            String roadName = rSet.getString(34)
                    + AppConstant.ONE_WHITESPACE
                    + rSet.getString(35);
            String house = rSet.getString(36);
            String building = rSet.getString(37);
            String room = rSet.getString(38);
            return new Address(addressId, zipCode, shortCountryName, regionName,
                    areaName, settleName, roadName, house, building, room);
        }
        return null;
    }

    private void changePatientPhoneNumber(String phoneNumber, int patientId,
            Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement phoneNumberInsertStatement = conn.prepareStatement("UPDATE hospital.persons p\n" +
                "SET p.phone_number = ?\n" +
                "WHERE p.person_id = ?");
        statements.add(phoneNumberInsertStatement);

        phoneNumberInsertStatement.setString(1, phoneNumber);
        phoneNumberInsertStatement.setInt(2, patientId);

        phoneNumberInsertStatement.executeUpdate();
    }

    private void changeMaritalStatus(MaritalStatus maritalStatus, int patientId,
            Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement mStatusUpdateStatement = conn.prepareStatement("UPDATE hospital.persons p\n" +
                "SET p.marital_status = ?\n" +
                "WHERE p.person_id = ?;");
        statements.add(mStatusUpdateStatement);

        mStatusUpdateStatement.setString(1, maritalStatus.name());
        mStatusUpdateStatement.setInt(2, patientId);

        mStatusUpdateStatement.executeUpdate();
    }

    private void changeIdentityDocument(IdentityDocument idDocument, int patientId,
            Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement idDocInsertStatement = conn
                .prepareStatement("INSERT INTO hospital.identification_documents (person_id, identification_document_type_id, series,\n" +
                        "                                               serial_number_of_document, latin_holder_name, latin_holder_surname,\n" +
                        "                                               citizenship_id, birth_date, personal_number, gender, place_of_origin,\n" +
                        "                                               date_of_issue, date_of_expiry, issue_authority)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
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

        PreparedStatement updatePatientWithIdDoc = conn.prepareStatement("UPDATE hospital.persons p\n" +
                "SET p.identification_document_id = ?\n" +
                "WHERE p.person_id = ?;");
        statements.add(updatePatientWithIdDoc);

        updatePatientWithIdDoc.setInt(1, idOfIdentityDocument);
        updatePatientWithIdDoc.setInt(2, patientId);

        updatePatientWithIdDoc.executeUpdate();
    }

    private void changeAddress(Address address, int patientId, Connection conn,
            ArrayList<PreparedStatement> statements) throws SQLException {
        PreparedStatement addressInsertStatement = conn.prepareStatement("INSERT INTO hospital.addresses (zip_code, road_id, house, building, room)\n" +
                "VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        statements.add(addressInsertStatement);

        addressInsertStatement.setString(1, address.getZipCode());
        int road_id = Integer.parseInt(address.getRoad());
        addressInsertStatement.setInt(2, road_id);
        addressInsertStatement.setString(3, address.getHouse());
        addressInsertStatement.setString(4, address.getBuilding());
        addressInsertStatement.setString(5, address.getRoom());

        addressInsertStatement.executeUpdate();
        int addressId = receiveGeneratedKeyAfterStatementExecution(addressInsertStatement);

        PreparedStatement updatePatientWithNewAddress = conn.prepareStatement("UPDATE hospital.persons p \n" +
                "SET p.permanent_home_address_id = ?\n" +
                "WHERE p.person_id = ?;");
        statements.add(updatePatientWithNewAddress);

        updatePatientWithNewAddress.setInt(1, addressId);
        updatePatientWithNewAddress.setInt(2, patientId);

        updatePatientWithNewAddress.executeUpdate();
    }

    private void changeEmergencyCaseContactPersonInfo(String emergencyContactPersonInfo,
            int patientId, Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement updatePatientWithEmergencyPersonInfo = conn
                .prepareStatement("UPDATE hospital.persons p\n" +
                        "SET p.in_case_of_emergency_person_id = ?\n" +
                        "WHERE p.person_id = ?;");
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
                .prepareStatement("UPDATE hospital.persons p\n" +
                        "SET p.in_case_of_emergency_phone_number = ?\n" +
                        "WHERE p.person_id = ?;");
        statements.add(updatePatientWithEmergencyPersonPhone);

        updatePatientWithEmergencyPersonPhone.setString(1,
                emergencyContactPhone);
        updatePatientWithEmergencyPersonPhone.setInt(2, patientId);

        updatePatientWithEmergencyPersonPhone.executeUpdate();
    }

    private void changeBloodType(BloodType bloodType, int patientId,
            Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement updatePatientWithBloodType = conn.prepareStatement("UPDATE hospital.persons p \n" +
                "SET p.blood_type = ?\n" +
                "WHERE p.person_id = ?;");
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
        PreparedStatement updatePatientWithRhGroup = conn.prepareStatement("UPDATE hospital.persons p\n" +
                "SET p.rhesus_factor = ?\n" +
                "WHERE p.person_id = ?;");
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
        PreparedStatement updatePatientWithDisability = conn.prepareStatement("UPDATE hospital.persons p\n" +
                "SET p.disability_degree = ?\n" +
                "WHERE p.person_id = ?;");
        statements.add(updatePatientWithDisability);

        updatePatientWithDisability.setString(1, disabilityDegree
                .name());
        updatePatientWithDisability.setInt(2, patientId);

        updatePatientWithDisability.executeUpdate();
    }

    private void changeTransportationStatus(TransportationStatus status,
            int patientId, Connection conn, ArrayList<PreparedStatement> statements)
            throws SQLException {
        PreparedStatement updateTransportationStatus = conn.prepareStatement("UPDATE hospital.persons p\n" +
                "SET p.transportation_status = ?\n" +
                "WHERE p.person_id = ?;");
        statements.add(updateTransportationStatus);

        updateTransportationStatus.setString(1, status.name());
        updateTransportationStatus.setInt(2, patientId);

        updateTransportationStatus.executeUpdate();
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

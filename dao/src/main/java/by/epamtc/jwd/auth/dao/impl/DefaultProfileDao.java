package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.ProfileDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
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
import java.time.LocalDate;

public class DefaultProfileDao implements ProfileDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public PatientInfo fetchPatientInfo(AuthUser authUser) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(SqlStatement.SELECT_PATIENT_INFO);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                return compilePatientInfo(rSet);
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
        return null;
    }

    private PatientInfo compilePatientInfo(ResultSet rSet) throws SQLException {
        String photoPath = rSet.getString(1);
        String firstName = rSet.getString(2);
        String middleName = rSet.getString(3);
        String lastName = rSet.getString(4);
        LocalDate birthday = (LocalDate) rSet.getObject(5);
        Gender gender = Gender.valueOf(rSet.getString(6));
        String email = rSet.getString(7);
        String phoneNumber = rSet.getString(8);
        MaritalStatus maritalStatus = MaritalStatus.valueOf(rSet.getString(9));

        IdentityDocument identityDocument = compileIdentityDocument(rSet);
        Address address = compileAddress(rSet);

        String inCaseOfEmergencyPerson = rSet.getString(39);
        String inCaseOfEmergencyPhone = rSet.getString(40);
        BloodType bloodType = BloodType.valueOf(rSet.getString(41));
        RhBloodGroup rhBlood = RhBloodGroup.valueOf(rSet.getString(42));
        DisabilityDegree disabilityDegree = DisabilityDegree.valueOf
                (rSet.getString(43));
        TransportationStatus transStatus = TransportationStatus.valueOf
                (rSet.getString(44));
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
            LocalDate idDocBirthday = (LocalDate) rSet.getObject(18);
            String personalNumber = rSet.getString(19);
            Gender idDocGender = Gender.valueOf(rSet.getString(20));
            String placeOfOrigin = rSet.getString(21);
            LocalDate dateOfIssue = (LocalDate) rSet.getObject(22);
            LocalDate dateOfExpiry = (LocalDate) rSet.getObject(23);
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
                    + rSet.getString(29);
            String areaName = rSet.getString(30)
                    + rSet.getString(31);
            String settleName = rSet.getString(32)
                    + rSet.getString(33);
            String roadName = rSet.getString(34)
                    + rSet.getString(35);
            String house = rSet.getString(36);
            String building = rSet.getString(37);
            String room = rSet.getString(38);
            return new Address(addressId, zipCode, shortCountryName, regionName,
                    areaName, settleName, roadName, house, building, room);
        }
        return null;
    }
}

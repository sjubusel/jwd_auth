package by.epamtc.jwd.auth.dao.util;

import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.user_info.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProfileRelatedEntitiesCompiler {
    private static volatile ProfileRelatedEntitiesCompiler instance;

    private ProfileRelatedEntitiesCompiler() {
    }

    public static ProfileRelatedEntitiesCompiler getInstance() {
        ProfileRelatedEntitiesCompiler localInstance = instance;
        if (localInstance == null) {
            synchronized (ProfileRelatedEntitiesCompiler.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ProfileRelatedEntitiesCompiler();
                }
            }
        }
        return localInstance;
    }

    public PatientInfo compilePatientInfo(ResultSet rSet) throws SQLException {
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

    public IdentityDocument compileIdentityDocument(ResultSet rSet)
            throws SQLException {
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

    public Address compileAddress(ResultSet rSet) throws SQLException {
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

    public MedicalHistoryPermission compileMedicalHistoryPermission(ResultSet
            rSet) throws SQLException {
        int permissionId = rSet.getInt(1);
        int recipientId = rSet.getInt(2);
        String recipientInfo;
        String firstName = rSet.getString(3);
        String middleName = rSet.getString(4);
        String lastName = rSet.getString(5);
        recipientInfo = (middleName != null)
                        ? firstName + AppConstant.ONE_WHITESPACE + middleName
                                + AppConstant.ONE_WHITESPACE + lastName
                        : firstName + AppConstant.ONE_WHITESPACE + lastName;
        LocalDateTime permissionDateTime = rSet.getTimestamp(6).toLocalDateTime();
        LocalDateTime cancellationDateTime = null;
        Timestamp cancellationTimeStamp = rSet.getTimestamp(7);
        if (cancellationTimeStamp != null) {
            cancellationDateTime = cancellationTimeStamp.toLocalDateTime();
        }
        String cancellationDescription = rSet.getString(8);
        return new MedicalHistoryPermission(permissionId, recipientId,
                recipientInfo, permissionDateTime, cancellationDateTime,
                cancellationDescription);
    }

    public ExtremelyHazardousDiseaseCase compileExtremelyHazardousDiseaseCase
            (ResultSet rSet) throws SQLException {
        int caseId = rSet.getInt(1);
        int diseaseId = rSet.getInt(2);
        String diseaseDescription = rSet.getString(3);
        LocalDate detectionDate = null;
        Date detection = rSet.getDate(4);
        if (detection != null) {
            detectionDate = detection.toLocalDate();
        }
        String caseDescription = rSet.getString(5);
        LocalDate recoveryDate = null;
        Date recovery = rSet.getDate(6);
        if (recovery != null) {
            recoveryDate = recovery.toLocalDate();
        }
        return new ExtremelyHazardousDiseaseCase(caseId, diseaseId,
                diseaseDescription, detectionDate, caseDescription, recoveryDate);
    }
}

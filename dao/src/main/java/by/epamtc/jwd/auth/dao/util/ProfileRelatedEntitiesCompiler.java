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

    public PatientInfo compilePatientInfo(ResultSet rSet, int startIndex)
            throws SQLException {
        String photoPath = rSet.getString(startIndex);
        String firstName = rSet.getString(++startIndex);
        String middleName = rSet.getString(++startIndex);
        String lastName = rSet.getString(++startIndex);
        LocalDate birthday = rSet.getDate(++startIndex).toLocalDate();
        Gender gender = null;
        String genderString = rSet.getString(++startIndex);
        if (genderString != null) {
            gender = Gender.valueOf(genderString);
        }

        String email = rSet.getString(++startIndex);
        String phoneNumber = rSet.getString(++startIndex);

        MaritalStatus maritalStatus = null;
        String maritalStatusString = rSet.getString(++startIndex);
        if (maritalStatusString != null) {
            maritalStatus = MaritalStatus.valueOf(maritalStatusString);
        }

        IdentityDocument identityDocument = compileIdentityDocument(rSet,
                ++startIndex);
        startIndex = startIndex + 15;
        Address address = compileAddress(rSet, startIndex);

        startIndex = startIndex + 14;
        String inCaseOfEmergencyPerson = rSet.getString(startIndex);
        String inCaseOfEmergencyPhone = rSet.getString(++startIndex);

        BloodType bloodType = null;
        String bloodTypeString = rSet.getString(++startIndex);
        if (bloodTypeString != null) {
            bloodType = BloodType.valueOf(bloodTypeString);
        }

        RhBloodGroup rhBlood = null;
        String rhBloodString = rSet.getString(++startIndex);
        if (rhBloodString != null) {
            rhBlood = RhBloodGroup.valueOf(rhBloodString);
        }

        DisabilityDegree disabilityDegree = null;
        String disabilityDegreeString = rSet.getString(++startIndex);
        if (disabilityDegreeString != null) {
            disabilityDegree = DisabilityDegree.valueOf(disabilityDegreeString);
        }

        TransportationStatus transStatus = null;
        String transStatusString = rSet.getString(++startIndex);
        if (transStatusString != null) {
            transStatus = TransportationStatus.valueOf(transStatusString);
        }

        boolean hasAllergyToFood = rSet.getBoolean(++startIndex);
        boolean hasAllergyToMedicine = rSet.getBoolean(++startIndex);
        boolean hasExtremelyHazardousDiseases = rSet.getBoolean(++startIndex);

        return new PatientInfo(photoPath, firstName, middleName, lastName,
                birthday, gender, email, phoneNumber, maritalStatus,
                identityDocument, address, inCaseOfEmergencyPerson,
                inCaseOfEmergencyPhone, bloodType, rhBlood, disabilityDegree,
                transStatus, (hasAllergyToFood || hasAllergyToMedicine),
                hasExtremelyHazardousDiseases);
    }

    public IdentityDocument compileIdentityDocument(ResultSet rSet,
            int startIndex) throws SQLException {
        int identityDocId = rSet.getInt(startIndex);
        if (identityDocId > 0) {
            int identityDocTypeId = rSet.getInt(++startIndex);
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
            identityDocType.setDescription(rSet.getString(++startIndex));
            String series = rSet.getString(++startIndex);
            int serialNumberOfDocument = rSet.getInt(++startIndex);
            String latinHolderName = rSet.getString(++startIndex);
            String latinHolderSurname = rSet.getString(++startIndex);
            String idDocCountryName = rSet.getString(++startIndex);
            LocalDate idDocBirthday = rSet.getDate(++startIndex).toLocalDate();
            String personalNumber = rSet.getString(++startIndex);
            Gender idDocGender = Gender.valueOf(rSet.getString(++startIndex));
            String placeOfOrigin = rSet.getString(++startIndex);
            LocalDate dateOfIssue = rSet.getDate(++startIndex).toLocalDate();
            LocalDate dateOfExpiry = rSet.getDate(++startIndex).toLocalDate();
            String issueAuthority = rSet.getString(++startIndex);
            return new IdentityDocument(identityDocId, identityDocType, series,
                    serialNumberOfDocument, latinHolderName, latinHolderSurname,
                    idDocCountryName, idDocBirthday, personalNumber,
                    idDocGender, placeOfOrigin, dateOfIssue, dateOfExpiry,
                    issueAuthority);
        }
        return null;
    }

    public Address compileAddress(ResultSet rSet, int startIndex) throws SQLException {
        int addressId = rSet.getInt(startIndex);
        if (addressId > 0) {
            String zipCode = rSet.getString(++startIndex);
            String shortCountryName = rSet.getString(++startIndex);
            String regionName = rSet.getString(++startIndex)
                    + AppConstant.ONE_WHITESPACE
                    + rSet.getString(++startIndex);
            String areaName = rSet.getString(++startIndex)
                    + AppConstant.ONE_WHITESPACE
                    + rSet.getString(++startIndex);
            String settleName = rSet.getString(++startIndex)
                    + AppConstant.ONE_WHITESPACE
                    + rSet.getString(++startIndex);
            String roadName = rSet.getString(++startIndex)
                    + AppConstant.ONE_WHITESPACE
                    + rSet.getString(++startIndex);
            String house = rSet.getString(++startIndex);
            String building = rSet.getString(++startIndex);
            String room = rSet.getString(++startIndex);
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

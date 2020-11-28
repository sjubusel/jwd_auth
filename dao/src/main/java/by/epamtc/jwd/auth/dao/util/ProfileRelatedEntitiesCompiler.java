package by.epamtc.jwd.auth.dao.util;

import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.user_info.Address;
import by.epamtc.jwd.auth.model.user_info.BloodType;
import by.epamtc.jwd.auth.model.user_info.DisabilityDegree;
import by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase;
import by.epamtc.jwd.auth.model.user_info.Gender;
import by.epamtc.jwd.auth.model.user_info.IdentificationDocumentType;
import by.epamtc.jwd.auth.model.user_info.IdentityDocument;
import by.epamtc.jwd.auth.model.user_info.MaritalStatus;
import by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.model.user_info.RhBloodGroup;
import by.epamtc.jwd.auth.model.user_info.TransportationStatus;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * a class which is created to compile entities which are to be used mostly in
 * profile subsystem
 */
public final class ProfileRelatedEntitiesCompiler {

    /**
     * a unique and only instance of this class
     */
    private static volatile ProfileRelatedEntitiesCompiler instance;

    /**
     * a private constructor of a Singleton pattern
     */
    private ProfileRelatedEntitiesCompiler() {
    }

    /**
     * a method which initialize and provide thread safe instance of a
     * current type
     *
     * @return an unique and only instance of a current class
     */
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

    /**
     * a method which generates by.epamtc.jwd.auth.model.user_info.PatientInfo
     * object using data stored in java.sql.ResultSet
     *
     * @param rSet       java.sql.ResultSet, which contains data to generate
     *                   by.epamtc.jwd.auth.model.user_info.PatientInfo
     * @param startIndex an int value, which is a number of the first column
     *                   in the result set
     * @return a by.epamtc.jwd.auth.model.user_info.PatientInfo object
     * that contains medical information about a patient
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     * @see by.epamtc.jwd.auth.model.user_info.PatientInfo
     */
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

    /**
     * a method that generates by.epamtc.jwd.auth.model.user_info.IdentityDocument
     * which contains information about a document that identifies a person
     *
     * @param rSet       java.sql.ResultSet, which contains data to generate
     *                   by.epamtc.jwd.auth.model.user_info.IdentityDocument
     * @param startIndex an int value, which is a number of the first column
     *                   in the result set
     * @return a by.epamtc.jwd.auth.model.user_info.IdentityDocument object
     * that contains information about a document that identifies a person
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     * @see by.epamtc.jwd.auth.model.user_info.IdentityDocument
     */
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

    /**
     * a method that generates by.epamtc.jwd.auth.model.user_info.Address
     * which contains information about an address beginning from country
     * ending up to a room
     *
     * @param rSet       java.sql.ResultSet, which contains data to generate
     *                   by.epamtc.jwd.auth.model.user_info.Address
     * @param startIndex an int value, which is a number of the first column
     *                   in the result set
     * @return a by.epamtc.jwd.auth.model.user_info.Address object
     * that contains information about an address beginning from country
     * ending up to a room
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     * @see by.epamtc.jwd.auth.model.user_info.Address
     */
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

    /**
     * a method that generates by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission
     * which contains information about a case of granting a permission from
     * a patient to another person
     *
     * @param rSet java.sql.ResultSet, which contains data to generate
     *             by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission
     * @return a by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission
     * that which contains information about a case of granting a permission from
     * a patient to another person
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     * @see by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission
     */
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

    /**
     * a method that generates by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase
     * which contains information about a case of development of extremely
     * hazardous disease (HIV, syphilis and etc)
     *
     * @param rSet java.sql.ResultSet, which contains data to generate
     *             by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase
     * @return a by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase
     * that which contains information about a case of development of extremely
     * hazardous disease (HIV, syphilis and etc)
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     * @see by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase
     */
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

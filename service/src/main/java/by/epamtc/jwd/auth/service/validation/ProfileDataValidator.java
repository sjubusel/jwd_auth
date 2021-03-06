package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.user_info.Address;
import by.epamtc.jwd.auth.model.user_info.IdentityDocument;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

import java.time.LocalDate;

public class ProfileDataValidator {
    public boolean isAuthUserValidForProfileActivity(AuthUser authUser) {
        if (authUser != null) {
            return authUser.getUserId() > 0;
        }
        return false;
    }

    public boolean isChangingPatientInfoValid(PatientInfo changingPatientInfo) {
        if (isImmutablePartOfPatientInfoChanged(changingPatientInfo)) {
            return false;
        }
        if (isMutablePartOfPatientInfoDamaged(changingPatientInfo)) {
            return false;
        }
        return true;
    }

    private boolean isImmutablePartOfPatientInfoChanged(PatientInfo patientInfo) {
        if (patientInfo.getFirstName() != null) {
            return true;
        }
        if (patientInfo.getMiddleName() != null) {
            return true;
        }
        if (patientInfo.getLastName() != null) {
            return true;
        }
        if (patientInfo.getBirthday() != null) {
            return true;
        }
        if (patientInfo.getGender() != null) {
            return true;
        }
        if (patientInfo.getPhotoPath() != null) {
            return true;
        }
        return patientInfo.getEmail() != null;
    }

    private boolean isMutablePartOfPatientInfoDamaged(PatientInfo changingInfo) {
        String phoneNumber = changingInfo.getPhoneNumber();
        if (phoneNumber != null && !phoneNumber.matches(RegistrationInfoPattern
                .PHONE_NUMBER)) {
            return true;
        }

        IdentityDocument idDocument = changingInfo.getIdentityDocument();
        if (idDocument != null && !isIdentityDocumentValid(idDocument)) {
            return true;
        }

        Address address = changingInfo.getHomeAddress();
        if (address != null && !isAddressValid(address)) {
            return true;
        }

        String emergencyPhone = changingInfo.getInCaseOfEmergencyContactPersonPhone();
        if (emergencyPhone != null && !emergencyPhone.matches
                (RegistrationInfoPattern.PHONE_NUMBER)) {
            return true;
        }

        return false;
    }

    private boolean isIdentityDocumentValid(IdentityDocument idDocument) {
        if (idDocument == null) {
            return true;
        }

        if (idDocument.getIdentificationDocumentType() == null) {
            return false;
        }

        String series = idDocument.getSeries();
        if (series != null && !series.matches(RegistrationInfoPattern.SERIES)) {
            return false;
        }

        int documentNumber = idDocument.getDocumentNumber();
        if (documentNumber <= 0) {
            return false;
        }

        String latinHolderName = idDocument.getLatinHolderName();
        if (latinHolderName == null) {
            return false;
        }
        if (!latinHolderName.matches(RegistrationInfoPattern.ANY_LATIN_NAME)) {
            return false;
        }

        String latinHolderSurName = idDocument.getLatinHolderSurName();
        if (latinHolderSurName == null) {
            return false;
        }
        if (!latinHolderSurName.matches(RegistrationInfoPattern.ANY_LATIN_NAME)) {
            return false;
        }

        String citizenShip = idDocument.getCitizenShip();
        if (citizenShip == null) {
            return false;
        }
        if (!citizenShip.matches(RegistrationInfoPattern.DIGITS)) {
            return false;
        }

        LocalDate birthday = idDocument.getBirthday();
        if (birthday == null) {
            return false;
        }
        LocalDate birthdayOfOlderPersonAlive = LocalDate.of(AppConstant
                        .OLDEST_PERSON_BIRTH_YEAR,
                AppConstant.OLDEST_PERSON_BIRTH_MONTH,
                AppConstant.OLDEST_PERSON_BIRTH_DAY);
        if (birthday.compareTo(birthdayOfOlderPersonAlive) <= 0
                || birthday.compareTo(LocalDate.now()) > 0) {
            return false;
        }


        String personalNumber = idDocument.getPersonalNumber();
        if (personalNumber == null) {
            return false;
        }
        if (!personalNumber.matches(RegistrationInfoPattern.PERSONAL_NUMBER)) {
            return false;
        }

        String placeOfOrigin = idDocument.getPlaceOfOrigin();
        if (placeOfOrigin == null) {
            return false;
        }
        if (!placeOfOrigin.matches(RegistrationInfoPattern.PLACE_OF_ORIGIN)) {
            return false;
        }

        LocalDate dateOfIssue = idDocument.getDateOfIssue();
        if (dateOfIssue == null) {
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        if (dateOfIssue.compareTo(currentDate) > 0) {
            return false;
        }

        LocalDate dateOfExpiry = idDocument.getDateOfExpiry();
        if (dateOfExpiry == null) {
            return false;
        }
        if (dateOfExpiry.compareTo(currentDate) <= 0) {
            return false;
        }

        String issueAuthority = idDocument.getIssueAuthority();
        if (issueAuthority == null) {
            return false;
        }

        return issueAuthority.matches(RegistrationInfoPattern.ISSUE_AUTHORITY);
    }

    private boolean isAddressValid(Address address) {
        if (address == null) {
            return true;
        }

        String zipCode = address.getZipCode();
        if ((zipCode == null) || !zipCode.matches(RegistrationInfoPattern
                .ZIP_CODE)) {
            return false;
        }

        String country = address.getCountry();
        if ((country == null) || !country.matches(RegistrationInfoPattern.DIGITS)) {
            return false;
        }

        String region = address.getRegion();
        if ((region == null) || !region.matches(RegistrationInfoPattern.DIGITS)) {
            return false;
        }

        String area = address.getArea();
        if ((area == null) || !area.matches(RegistrationInfoPattern.DIGITS)) {
            return false;
        }

        String settlement = address.getSettlement();
        if ((settlement == null) || !settlement.matches(RegistrationInfoPattern
                .DIGITS)) {
            return false;
        }

        String road = address.getRoad();
        if ((road == null) || !road.matches(RegistrationInfoPattern.DIGITS)) {
            return false;
        }

        String house = address.getHouse();
        if ((house == null) || !house.matches(RegistrationInfoPattern.HOUSE)) {
            return false;
        }

        String building = address.getBuilding();
        if ((building != null) && !building.matches(RegistrationInfoPattern
                .BUILDING)) {
            return false;
        }

        String room = address.getRoom();
        if ((room != null) && !room.matches(RegistrationInfoPattern.ROOM)) {
            return false;
        }

        return true;
    }
}

package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.user_info.Address;
import by.epamtc.jwd.auth.model.user_info.IdentityDocument;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

import java.time.LocalDate;

public class ProfileAuthUserValidator {
    private RegistrationInfoValidator regInfoValidator
            = new RegistrationInfoValidator();

    public boolean isAuthUserValidToFetchPatientInfo(AuthUser authUser) {
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
        if (series != null && !series.matches("[0-9A-Za-z]{1,255}")) {
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
        if (!latinHolderName.matches("[A-Za-z -]{1,255}")) {
            return false;
        }

        String latinHolderSurName = idDocument.getLatinHolderSurName();
        if (latinHolderSurName == null) {
            return false;
        }
        if (!latinHolderSurName.matches("[A-Za-z -]{1,255}")) {
            return false;
        }

        String citizenShip = idDocument.getCitizenShip();
        if (citizenShip == null) {
            return false;
        }
        if (!citizenShip.matches("[0-9]+")) {
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
        if (!personalNumber.matches("[0-9A-Za-z]{1,255}")) {
            return false;
        }

        String placeOfOrigin = idDocument.getPlaceOfOrigin();
        if (placeOfOrigin == null) {
            return false;
        }
        if (!placeOfOrigin.matches("[0-9A-Za-zА-Яа-яЁё \\-,.]{1,255}")) {
            return false;
        }

        LocalDate dateOfIssue = idDocument.getDateOfIssue();
        if (dateOfIssue == null) {
            return false;
        }
        LocalDate currentDate = LocalDate.now();
//        LocalDate startDate = currentDate.minusYears(21);
        if (dateOfIssue.compareTo(currentDate) > 0) {
            return false;
        }

        LocalDate dateOfExpiry = idDocument.getDateOfExpiry();
        if (dateOfExpiry == null) {
            return false;
        }
//        LocalDate endDate = currentDate.plusYears(21);
        if (dateOfExpiry.compareTo(currentDate) <= 0) {
            return false;
        }

        String issueAuthority = idDocument.getIssueAuthority();
        if (issueAuthority == null) {
            return false;
        }

        return issueAuthority.matches("[0-9A-Za-zА-Яа-яЁё \\-,.]{1,255}");
    }

    private boolean isAddressValid(Address address) {
        if (address == null) {
            return true;
        }

        String zipCode = address.getZipCode();
        if ((zipCode == null) || !zipCode.matches("[0-9A-Za-z \\-]{1,255}")) {
            return false;
        }

        String country = address.getCountry();
        if ((country == null) || !country.matches("[0-9]+")) {
            return false;
        }

        String region = address.getRegion();
        if ((region == null) || !region.matches("[0-9]+")) {
            return false;
        }

        String area = address.getArea();
        if ((area == null) || !area.matches("[0-9]+")) {
            return false;
        }

        String settlement = address.getSettlement();
        if ((settlement == null) || !settlement.matches("[0-9]+")) {
            return false;
        }

        String road = address.getRoad();
        if ((road == null) || !road.matches("[0-9]+")) {
            return false;
        }

        String house = address.getHouse();
        if ((house == null) || !house.matches("[0-9А-Яа-яЁё \\-]{1,255}")) {
            return false;
        }

        String building = address.getBuilding();
        if ((building != null) && !building.matches("[0-9А-Яа-яЁё \\-]{1,255}")) {
            return false;
        }

        String room = address.getRoom();
        if ((room != null) && !room.matches("[0-9А-Яа-яЁё \\-]{1,255}")) {
            return false;
        }

        return true;
    }
}

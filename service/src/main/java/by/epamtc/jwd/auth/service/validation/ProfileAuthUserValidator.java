package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

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
            return false;
        }
        if (patientInfo.getMiddleName() != null) {
            return false;
        }
        if (patientInfo.getLastName() != null) {
            return false;
        }
        if (patientInfo.getBirthday() != null) {
            return false;
        }
        if (patientInfo.getGender() != null) {
            return false;
        }
        if (patientInfo.getPhotoPath() != null) {
            return false;
        }
        return patientInfo.getEmail() == null;
    }

    private boolean isMutablePartOfPatientInfoDamaged(PatientInfo changingInfo) {

        return false;
    }
}

package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;

public class ProfileAuthUserValidator {
    public boolean isAuthUserValidToFetchPatientInfo(AuthUser authUser) {
        if (authUser != null) {
            return authUser.getId() >= 0;
        }
        return false;
    }
}

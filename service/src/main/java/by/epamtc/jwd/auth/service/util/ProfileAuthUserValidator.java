package by.epamtc.jwd.auth.service.util;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;

public class ProfileAuthUserValidator {
    public boolean isAuthUserValidToFetchPatientInfo(AuthUser authUser) {
        return authUser.getId() >= 0;
    }
}

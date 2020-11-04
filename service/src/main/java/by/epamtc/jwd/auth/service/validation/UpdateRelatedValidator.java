package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;

import java.io.InputStream;

public class UpdateRelatedValidator {

    public boolean isInitDataValidToUpdate(String targetFileName,
            InputStream iFileStreamFromClient, AuthUser user) {
        if (user == null) {
            return false;
        }
        if (user.getUserId() <= 0) {
            return false;
        }
        return (targetFileName != null) && (iFileStreamFromClient != null);
    }
}

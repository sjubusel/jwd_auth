package by.epamtc.jwd.auth.service.util;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;

public final class DuplicateAuthUserProvider {
    private static volatile DuplicateAuthUserProvider instance;

    private final AuthUser bothDuplicateAuthUser = new AuthUser(
            AppConstant.DUPLICATE_AUTH_USER_LOGIN_ID,
            AppConstant.DUPLICATE_AUTH_USER_EMAIL_ID);
    private final AuthUser loginDuplicateAuthUser = new AuthUser(
            AppConstant.DUPLICATE_AUTH_USER_LOGIN_ID,
            AppConstant.AUTH_USER_STANDARD_INT_VALUE);
    private final AuthUser emailDuplicateAuthUser = new AuthUser(
            AppConstant.AUTH_USER_STANDARD_INT_VALUE,
            AppConstant.DUPLICATE_AUTH_USER_EMAIL_ID);

    private DuplicateAuthUserProvider() {
    }

    public static DuplicateAuthUserProvider getInstance() {
        DuplicateAuthUserProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (DuplicateAuthUserProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DuplicateAuthUserProvider();
                }
            }
        }
        return localInstance;
    }

    public AuthUser receiveAuthUserDuplicate(boolean doesLoginExist,
            boolean doesEmailExist) {
        if (doesLoginExist && doesEmailExist) {
            return bothDuplicateAuthUser;
        }
        if (doesLoginExist) {
            return loginDuplicateAuthUser;
        }
        if (doesEmailExist) {
            return emailDuplicateAuthUser;
        }
        return null;
    }
}

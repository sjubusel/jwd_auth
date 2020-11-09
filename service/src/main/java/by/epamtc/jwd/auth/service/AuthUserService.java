package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.AuthenticationInfo;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.service.exception.ServiceException;

public interface AuthUserService {
    AuthUser login(AuthenticationInfo authenticationInfo) throws ServiceException;

    AuthUser register(RegistrationInfo registrationInfo) throws ServiceException;

    String changeEmail(String email, String password, AuthUser user)
            throws ServiceException;
}

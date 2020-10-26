package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.service.exception.ServiceException;

public interface AuthUserService {
    AuthUser login(String login, byte[] password) throws ServiceException;

    AuthUser register(RegistrationInfo registrationInfo) throws ServiceException;
}

package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_user.AuthUser;
import by.epamtc.jwd.auth.service.exception.ServiceException;

public interface AuthUserService {
    AuthUser login(String login, String password) throws ServiceException;

    AuthUser register(String login, String password) throws ServiceException;

}

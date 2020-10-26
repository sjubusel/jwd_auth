package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;

public interface AuthUserDao {
    AuthUser receiveAuthUserIfCorrect(String login, byte[] password)
            throws DaoException;

    AuthUser saveAuthUser(RegistrationInfo registrationInfo) throws DaoException;

    boolean containsLogin(String login) throws DaoException;

    boolean containsEmail(String email);
}

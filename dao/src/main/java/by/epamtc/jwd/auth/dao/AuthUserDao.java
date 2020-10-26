package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;

public interface AuthUserDao {
    AuthUser receiveAuthUserIfCorrect(String login, byte[] password)
            throws DaoException;

    int saveAuthUser(AuthUser user) throws DaoException;

    boolean containsLogin(String login) throws DaoException;

    boolean containsEmail(String email);
}

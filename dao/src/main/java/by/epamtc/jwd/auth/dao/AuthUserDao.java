package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_user.AuthUser;

public interface AuthUserDao {
    AuthUser receiveAuthUserIfCorrect(String login, String password)
            throws DaoException;

    int saveAuthUser(AuthUser user) throws DaoException;

    boolean containsLogin(String login) throws DaoException;
}

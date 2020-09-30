package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.AuthUserDao;
import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_user.AuthUser;
import by.epamtc.jwd.auth.model.auth_user.Role;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.validation.AuthDataValidator;

import java.nio.charset.StandardCharsets;

public class DefaultAuthUserService implements AuthUserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private AuthUserDao authUserDao = daoFactory.getAuthUserDao();
    private AuthDataValidator authDataValidator = new AuthDataValidator();

    @Override
    public AuthUser login(String login, byte[] password) throws ServiceException {
        AuthUser user;
        try {
            user = authUserDao.receiveAuthUserIfCorrect(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public AuthUser register(String login, String password) throws ServiceException {
        if (authDataValidator.isAuthDataValidOtherwiseThrow(login, password)) {
            try {
                if (!authUserDao.containsLogin(login)) {
                    authUserDao.saveAuthUser(new AuthUser(login, password,
                            Role.USER));
                    return authUserDao.receiveAuthUserIfCorrect(login,
                            password.getBytes(StandardCharsets.UTF_8));
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }

        return null;
    }
}

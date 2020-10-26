package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.AuthUserDao;
import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.validation.RegistrationInfoValidator;
import org.mindrot.jbcrypt.BCrypt;

public class DefaultAuthUserService implements AuthUserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private AuthUserDao authUserDao = daoFactory.getAuthUserDao();
    private RegistrationInfoValidator regInfValidator
            = new RegistrationInfoValidator();

    @Override
    public AuthUser login(String login, byte[] password)
            throws ServiceException {
        AuthUser user;
        try {
            user = authUserDao.receiveAuthUserIfCorrect(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            cleanOutPassword(password);
        }
        return user;
    }

    @Override
    public AuthUser register(RegistrationInfo regInfo)
            throws ServiceException {
        if (regInfValidator.isRegistrationInfoValid(regInfo)) {
            try {
                if (!authUserDao.containsLogin(regInfo.getLogin())
                        && !authUserDao.containsEmail(regInfo.getEmail())) {
                    hashPassword(regInfo);
                    return authUserDao.saveAuthUser(regInfo);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            } finally {
                cleanOutPassword(regInfo);
            }
        }
        return null;
    }

    private void hashPassword(RegistrationInfo regInfo) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(regInfo.getPassword(), salt);
        regInfo.setPassword(hashedPassword);
    }

    private void cleanOutPassword(RegistrationInfo registrationInfo) {
        registrationInfo.setPassword(null);
    }

    private void cleanOutPassword(byte[] passwordBytes) {
        // TODO ??? delete ???
        for (int i = 0; i < passwordBytes.length; i++) {
            passwordBytes[i] = 0;
        }
    }

}

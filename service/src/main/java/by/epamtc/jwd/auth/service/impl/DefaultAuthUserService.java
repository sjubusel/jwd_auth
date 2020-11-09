package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.AuthUserDao;
import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.AuthenticationInfo;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.model.constant.ChangeResult;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.util.DuplicateAuthUserProvider;
import by.epamtc.jwd.auth.service.validation.RegistrationInfoValidator;
import org.mindrot.jbcrypt.BCrypt;

public class DefaultAuthUserService implements AuthUserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private AuthUserDao authUserDao = daoFactory.getAuthUserDao();
    private RegistrationInfoValidator regInfValidator
            = new RegistrationInfoValidator();
    private DuplicateAuthUserProvider duplicateAuthUserProvider
            = DuplicateAuthUserProvider.getInstance();

    @Override
    public AuthUser login(AuthenticationInfo authenticationInfo)
            throws ServiceException {
        if (regInfValidator.isAuthenticationInfoValid(authenticationInfo)) {
            try {
                return authUserDao.receiveAuthUserIfCorrect(authenticationInfo);
            } catch (DaoException e) {
                throw new ServiceException(e);
            } finally {
                cleanOutPassword(authenticationInfo);
            }
        }
        return null;
    }

    @Override
    public AuthUser register(RegistrationInfo regInfo)
            throws ServiceException {
        if (regInfValidator.isRegistrationInfoValid(regInfo)) {
            try {
                boolean doesLoginExist = authUserDao.containsLogin(regInfo
                        .getLogin());
                boolean doesEmailExist = authUserDao.containsEmail(regInfo
                        .getEmail());
                if (doesLoginExist | doesEmailExist) {
                    return duplicateAuthUserProvider
                            .receiveAuthUserDuplicate(doesLoginExist,
                                    doesEmailExist);
                }

                hashPassword(regInfo);
                return authUserDao.saveAuthUser(regInfo);
            } catch (DaoException e) {
                throw new ServiceException(e);
            } finally {
                cleanOutPassword(regInfo);
            }
        }
        return null;
    }

    @Override
    public String changeEmail(String email, String password, AuthUser user)
            throws ServiceException {
        if (regInfValidator.isEmailValid(email)) {
            try {
                if (authUserDao.containsEmail(email)) {
                    return ChangeResult.DUPLICATE_ERROR.name();
                }
                return authUserDao.changeEmailIfCorrect(email, password, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }

        return null;
    }

    @Override
    public String changePassword(String newPassword, String password,
            AuthUser user) throws ServiceException {
        if (regInfValidator.isPasswordValid(newPassword)
                && regInfValidator.isPasswordValid(password)) {
            try {
                return authUserDao.changePasswordIfCorrect(newPassword, password
                        , user);
            } catch (DaoException e) {
                throw new ServiceException(e);
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

    private void cleanOutPassword(AuthenticationInfo authenticationInfo) {
        authenticationInfo.setPassword(null);
    }

}

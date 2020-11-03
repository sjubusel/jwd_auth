package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.ProfileDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.service.ProfileService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.util.ProfileAuthUserValidator;


public class DefaultProfileService implements ProfileService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private ProfileDao profileDao = daoFactory.getProfileDao();
    private ProfileAuthUserValidator validator
            = new ProfileAuthUserValidator();

    @Override
    public PatientInfo fetchPatientInfo(AuthUser authUser)
            throws ServiceException {
        if (validator.isAuthUserValidToFetchPatientInfo(authUser)) {
            try {
                return profileDao.fetchPatientInfo(authUser);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }
}

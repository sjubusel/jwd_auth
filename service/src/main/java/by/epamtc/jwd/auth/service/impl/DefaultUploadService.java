package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.UpdateDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.service.UploadService;
import by.epamtc.jwd.auth.service.exception.ServiceException;

public class DefaultUploadService implements UploadService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UpdateDao updateDao = daoFactory.getUpdateDao();

    @Override
    public boolean updatePatientPhoto(String targetFileName, AuthUser user)
            throws ServiceException {
        // validator if targetfilename exists and has Correct naming
        // validator if user contains all necessary info in upload
        try {
            return updateDao.updatePatientPhoto(targetFileName, user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

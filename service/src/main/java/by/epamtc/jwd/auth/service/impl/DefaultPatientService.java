package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.PatientDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.service.PatientService;
import by.epamtc.jwd.auth.service.exception.ServiceException;

public class DefaultPatientService implements PatientService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private PatientDao patientDao = daoFactory.getPatientDao();

    @Override
    public AdmissionDepartmentVisit fetchFullVisitIfExist(AuthUser user)
            throws ServiceException {
        if (user.getUserId() > 0) {
            try {
                return patientDao.fetchFullVisitIfExist(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }
}

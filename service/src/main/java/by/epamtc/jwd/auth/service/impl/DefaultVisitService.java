package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.VisitDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.service.VisitService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.validation.VisitValidator;

public class DefaultVisitService implements VisitService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private VisitDao visitDao = daoFactory.getVisitDao();
    private VisitValidator validator = new VisitValidator();

    @Override
    public boolean registerVisit(AdmissionDepartmentVisit hospVisit)
            throws ServiceException {
        if (validator.isAdmissionDepartmentVisit(hospVisit)) {
            try {
                return visitDao.registerVisit(hospVisit);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }
}

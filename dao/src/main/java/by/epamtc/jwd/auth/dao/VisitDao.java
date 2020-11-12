package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;

public interface VisitDao {
    boolean registerVisit(AdmissionDepartmentVisit hospVisit)
            throws DaoException;
}

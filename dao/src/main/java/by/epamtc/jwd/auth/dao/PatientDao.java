package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;

public interface PatientDao {
    AdmissionDepartmentVisit fetchFullVisitIfExist(AuthUser user) throws DaoException;
}

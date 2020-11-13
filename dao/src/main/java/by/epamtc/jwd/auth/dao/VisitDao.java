package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;

import java.util.List;

public interface VisitDao {
    boolean registerVisit(AdmissionDepartmentVisit hospVisit)
            throws DaoException;

    List<AdmissionDepartmentVisit> fetchNewVisits(AuthUser user)
            throws DaoException;

    boolean acceptPatientForTreatment(String visitId, AuthUser user)
            throws DaoException;

    List<AdmissionDepartmentVisit> fetchControlledVisits(AuthUser user)
            throws DaoException;

    AdmissionDepartmentVisit fetchFullAdmissionDepartmentVisit(String visitId)
            throws DaoException;
}

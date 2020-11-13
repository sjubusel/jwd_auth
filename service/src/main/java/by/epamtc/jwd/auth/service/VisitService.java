package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.util.List;

public interface VisitService {
    boolean registerVisit(AdmissionDepartmentVisit hospVisit)
            throws ServiceException;

    List<AdmissionDepartmentVisit> fetchNewVisits(AuthUser user)
            throws ServiceException;

    boolean acceptPatientForTreatment(String visitId, AuthUser user)
            throws ServiceException;

    List<AdmissionDepartmentVisit> fetchControlledVisits(AuthUser user)
            throws ServiceException;

    AdmissionDepartmentVisit fetchFullAdmissionDepartmentVisit(String visitId)
            throws ServiceException;

    List<Diagnosis> fetchInnerHospitalDiagnoses(int patientId)
            throws ServiceException;
}

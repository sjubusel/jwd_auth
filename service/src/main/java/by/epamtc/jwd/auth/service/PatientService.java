package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.util.List;

public interface PatientService {
    AdmissionDepartmentVisit fetchFullVisitIfExist(AuthUser user)
            throws ServiceException;

    List<MedicinePrescription> fetchAllNewMedicinePrescriptions(AuthUser user)
            throws ServiceException;
}

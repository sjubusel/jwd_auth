package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.RefusalReference;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.util.List;

public interface PatientService {
    AdmissionDepartmentVisit fetchFullVisitIfExist(AuthUser user)
            throws ServiceException;

    List<MedicinePrescription> fetchAllNewMedicinePrescriptions(AuthUser user)
            throws ServiceException;

    boolean disagreeWithMedicinePrescription(AuthUser user, String prescriptionId,
            String disagreementDescription) throws ServiceException;

    List<Prescription> fetchAllNewPrescriptions(AuthUser user)
            throws ServiceException;

    boolean disagreeWithPrescription(AuthUser user, String prescriptionId,
            String disagreementDescription) throws ServiceException;

    List<Diagnosis> fetchDiagnosesForPatientDuringVisit(AuthUser user,
            int visitId) throws ServiceException;

    List<MedicinePrescription> fetchMedPrescriptionsFinishedDuringVisit(
            AuthUser user, int visitId) throws ServiceException;

    List<Prescription> fetchPrescriptionsFinishedDuringVisit(AuthUser user,
            int visitId) throws ServiceException;

    List<RefusalReference> fetchRefusalReferences(String pageNumber,
            AuthUser user) throws ServiceException;
}

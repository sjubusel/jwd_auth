package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.RefusalReference;

import java.util.List;

public interface PatientDao {
    AdmissionDepartmentVisit fetchFullVisitIfExist(AuthUser user)
            throws DaoException;

    List<MedicinePrescription> fetchAllNewMedicinePrescriptions(AuthUser user)
            throws DaoException;

    int fetchPatientIdByMedicinePrescriptionId(String prescriptionId)
            throws DaoException;

    boolean disagreeWithMedicinePrescription(String prescriptionId,
            String disagreementDescription) throws DaoException;

    List<Prescription> fetchAllNewPrescriptions(AuthUser user)
            throws DaoException;

    int fetchPatientIdByPrescriptionId(String prescriptionId)
            throws DaoException;

    boolean disagreeWithPrescription(String prescriptionId,
            String disagreementDescription) throws DaoException;

    int fetchPatientIdByVisitId(int visitId) throws DaoException;

    List<Diagnosis> fetchDiagnosesForPatientDuringVisit(int visitId)
            throws DaoException;

    List<MedicinePrescription> fetchMedPrescriptionsFinishedDuringVisit(int visitId)
            throws DaoException;

    List<Prescription> fetchPrescriptionsFinishedDuringVisit(int visitId)
            throws DaoException;

    List<RefusalReference> fetchRefusalReferences(String pageNumber,
            AuthUser user) throws DaoException;
}

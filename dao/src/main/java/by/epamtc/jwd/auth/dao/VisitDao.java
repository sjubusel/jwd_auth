package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
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

    List<Diagnosis> fetchInnerHospitalDiagnoses(int patientId)
            throws DaoException;

    List<MedicinePrescription> fetchVisitMedicinePrescriptions(String visitId)
            throws DaoException;

    List<Prescription> fetchVisitPrescriptions(String visitId)
            throws DaoException;

    boolean changeComplaints(String complaints, String visitId, AuthUser user)
            throws DaoException;

    boolean establishDiagnosis(Diagnosis diagnosis, String visitStrId,
            AuthUser user) throws DaoException;

    boolean establishMedicinePrescription(MedicinePrescription prescription,
            AuthUser user) throws DaoException;

    boolean establishPrescription(Prescription prescription, AuthUser user)
            throws DaoException;

    boolean cancelPrescription(String prescriptionId, AuthUser user)
            throws DaoException;

    boolean cancelMedicinePrescription(String medPrescriptionId, AuthUser user)
            throws DaoException;

    List<MedicinePrescription> fetchAllNewMedicinePrescriptions()
            throws DaoException;

    MedicinePrescription fetchVisitMedPrescriptionById(String medPrescriptionId)
            throws DaoException;

    boolean executeMedPrescription(String medPrescriptionId,
            String executionResult, AuthUser user) throws DaoException;

    List<Prescription> fetchAllNewNonMedicinePrescriptions()
            throws DaoException;

}

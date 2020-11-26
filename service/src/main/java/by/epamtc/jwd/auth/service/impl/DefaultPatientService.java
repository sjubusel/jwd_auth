package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.PatientDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.service.PatientService;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.util.List;

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

    @Override
    public List<MedicinePrescription> fetchAllNewMedicinePrescriptions(AuthUser user) throws ServiceException {
        if (user.getUserId() > 0) {
            try {
                return patientDao.fetchAllNewMedicinePrescriptions(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean disagreeWithMedicinePrescription(AuthUser user,
            String prescriptionId, String disagreementDescription)
            throws ServiceException {
        try {
            if (prescriptionId.matches(RegistrationInfoPattern.DIGITS)
                    && (patientDao.fetchPatientIdByMedicinePrescriptionId(
                    prescriptionId) == user.getUserId())) {
                return patientDao.disagreeWithMedicinePrescription(prescriptionId,
                        disagreementDescription);
            }
            return false;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> fetchAllNewPrescriptions(AuthUser user)
            throws ServiceException {
        if (user.getUserId() > 0) {
            try {
                return patientDao.fetchAllNewPrescriptions(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean disagreeWithPrescription(AuthUser user, String prescriptionId,
            String disagreementDescription) throws ServiceException {
        try {
            if (prescriptionId.matches(RegistrationInfoPattern.DIGITS)
                    && (patientDao.fetchPatientIdByPrescriptionId(prescriptionId)
                    == user.getUserId())) {
                return patientDao.disagreeWithPrescription(prescriptionId,
                        disagreementDescription);
            }
            return false;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Diagnosis> fetchDiagnosesForPatientDuringVisit(AuthUser user,
            int visitId) throws ServiceException {
        try {
            if (visitId > 0 && (patientDao.fetchPatientIdByVisitId(visitId)
                    == user.getUserId())) {
                return patientDao.fetchDiagnosesForPatientDuringVisit(visitId);
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<MedicinePrescription> fetchMedPrescriptionsFinishedDuringVisit(
            AuthUser user, int visitId) throws ServiceException {
        try {
            if (visitId > 0 && (patientDao.fetchPatientIdByVisitId(visitId)
                    == user.getUserId())) {
                return patientDao.fetchMedPrescriptionsFinishedDuringVisit(visitId);
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> fetchPrescriptionsFinishedDuringVisit(
            AuthUser user, int visitId) throws ServiceException {
        try {
            if (visitId > 0 && (patientDao.fetchPatientIdByVisitId(visitId)
                    == user.getUserId())) {
                return patientDao.fetchPrescriptionsFinishedDuringVisit(visitId);
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

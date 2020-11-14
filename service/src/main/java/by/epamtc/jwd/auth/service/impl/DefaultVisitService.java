package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.VisitDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.service.VisitService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.validation.VisitValidator;

import java.util.List;

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

    @Override
    public List<AdmissionDepartmentVisit> fetchNewVisits(AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserHasRights(user)) {
            try {
                return visitDao.fetchNewVisits(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean acceptPatientForTreatment(String visitId, AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserHasRights(user) && validator
                .isVisitIdCorrect(visitId)) {
            try {
                return visitDao.acceptPatientForTreatment(visitId, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public List<AdmissionDepartmentVisit> fetchControlledVisits(AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserHasRights(user)) {
            try {
                return visitDao.fetchControlledVisits(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public AdmissionDepartmentVisit fetchFullAdmissionDepartmentVisit(String
            visitId) throws ServiceException {
        if (validator.isVisitIdCorrect(visitId)) {
            try {
                return visitDao.fetchFullAdmissionDepartmentVisit(visitId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<Diagnosis> fetchInnerHospitalDiagnoses(int patientId)
            throws ServiceException {
        if (validator.isPatientIdCorrect(patientId)) {
            try {
                return visitDao.fetchInnerHospitalDiagnoses(patientId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<MedicinePrescription> fetchVisitMedicinePrescriptions
            (String visitId) throws ServiceException {
        if (validator.isVisitIdCorrect(visitId)) {
            try {
                return visitDao.fetchVisitMedicinePrescriptions(visitId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<Prescription> fetchVisitPrescriptions(String visitId)
            throws ServiceException {
        if (validator.isVisitIdCorrect(visitId)) {
            try {
                return visitDao.fetchVisitPrescriptions(visitId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean changeComplaints(String complaints, String visitId,
            AuthUser user) throws ServiceException {
        if (validator.isVisitIdCorrect(visitId)
                && validator.isAuthUserHasRights(user)) {
            try {
                return visitDao.changeComplaints(complaints, visitId, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean establishDiagnosis(Diagnosis diagnosis, String visitStrId,
            AuthUser user) throws ServiceException {
        if (validator.isVisitIdCorrect(visitStrId)
                && validator.isDiagnosisCorrect(diagnosis)
                && validator.isAuthUserHasRights(user)) {
            try {
                return visitDao.establishDiagnosis(diagnosis, visitStrId,
                        user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }
}

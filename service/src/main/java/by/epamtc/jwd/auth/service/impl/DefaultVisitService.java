package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.VisitDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.RefusalMedicineRecommendation;
import by.epamtc.jwd.auth.service.VisitService;
import by.epamtc.jwd.auth.service.exception.AllergicServiceException;
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
        if (validator.isAuthUserHasRightsToTreat(user)) {
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
        if (validator.isAuthUserHasRightsToTreat(user) && validator
                .isStringIdCorrect(visitId)) {
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
        if (validator.isAuthUserHasRightsToTreat(user)) {
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
        if (validator.isStringIdCorrect(visitId)) {
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
        if (validator.isStringIdCorrect(visitId)) {
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
        if (validator.isStringIdCorrect(visitId)) {
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
        if (validator.isStringIdCorrect(visitId)
                && validator.isAuthUserHasRightsToTreat(user)) {
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
        if (validator.isStringIdCorrect(visitStrId)
                && validator.isDiagnosisCorrect(diagnosis)
                && validator.isAuthUserHasRightsToTreat(user)) {
            try {
                return visitDao.establishDiagnosis(diagnosis, visitStrId,
                        user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean establishMedicinePrescription(MedicinePrescription prescription,
            AuthUser user) throws ServiceException {
        if (validator.isMedicinePrescriptionValid(prescription)
                && validator.isAuthUserHasRightsToTreat(user)) {
            try {
                boolean isEstablished = visitDao.establishMedicinePrescription
                        (prescription, user);
                if (!isEstablished) {
                    throw new AllergicServiceException("An error while " +
                            "doctor tried to establish a medicine, to one " +
                            "of which components a patient has an allergic " +
                            "reaction");
                }
                return true;
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean establishPrescription(Prescription prescription, AuthUser user)
            throws ServiceException {
        if (validator.isPrescriptionValid(prescription)
                && validator.isAuthUserHasRightsToTreat(user)) {
            try {
                return visitDao.establishPrescription(prescription, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean cancelPrescription(String prescriptionId, AuthUser user)
            throws ServiceException {
        if (validator.isStringIdCorrect(prescriptionId)
                && validator.isAuthUserHasRightsToTreat(user)) {
            try {
                return visitDao.cancelPrescription(prescriptionId, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean cancelMedicinePrescription(String medPrescriptionId,
            AuthUser user) throws ServiceException {
        if (validator.isStringIdCorrect(medPrescriptionId)
                && validator.isAuthUserHasRightsToTreat(user)) {
            try {
                return visitDao.cancelMedicinePrescription(medPrescriptionId,
                        user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public List<MedicinePrescription> fetchAllNewMedicinePrescriptions
            (AuthUser user) throws ServiceException {
        if (validator.isAuthUserHasRightsToExecuteMedicinePrescriptions(user)) {
            try {
                return visitDao.fetchAllNewMedicinePrescriptions();
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public MedicinePrescription fetchVisitMedPrescriptionById(
            String medPrescriptionId, AuthUser user) throws ServiceException {
        if (validator.isAuthUserHasRightsToExecuteMedicinePrescriptions(user)
                && validator.isStringIdCorrect(medPrescriptionId)) {
            try {
                return visitDao.fetchVisitMedPrescriptionById(medPrescriptionId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean executeMedPrescription(String medPrescriptionId,
            String executionResult, AuthUser user) throws ServiceException {
        if (validator.isAuthUserHasRightsToExecuteMedicinePrescriptions(user)
                && validator.isStringIdCorrect(medPrescriptionId)) {
            try {
                return visitDao.executeMedPrescription(medPrescriptionId,
                        executionResult, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public List<Prescription> fetchAllNewNonMedicinePrescriptions(AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserHasRightsToExecuteMedicinePrescriptions(user)) {
            try {
                return visitDao.fetchAllNewNonMedicinePrescriptions();
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean acceptPrescriptionOnControl(String prescriptionId, AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserHasRightsToControlPrescriptions(user)
                && validator.isStringIdCorrect(prescriptionId)) {
            try {
                return visitDao.acceptPrescriptionOnControl(prescriptionId, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public List<Prescription> fetchControlledVisitPrescriptions(AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserHasRightsToControlPrescriptions(user)) {
            try {
                return visitDao.fetchControlledVisitPrescriptions(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public Prescription fetchVisitPrescriptionById(String prescriptionId,
            AuthUser user) throws ServiceException {
        if (validator.isAuthUserHasRightsToExecutePrescriptions(user)
                && validator.isStringIdCorrect(prescriptionId)) {
            try {
                return visitDao.fetchVisitPrescriptionById(prescriptionId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean executePrescription(String prescriptionId,
            String executionResult, AuthUser user) throws ServiceException {
        if (validator.isAuthUserHasRightsToExecutePrescriptions(user)
                && validator.isStringIdCorrect(prescriptionId)) {
            try {
                return visitDao.executePrescription(prescriptionId, executionResult,
                        user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean startRefusalProcedure(String visitId, AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserHasRightsToTreat(user)
                && validator.isStringIdCorrect(visitId)) {
            try {
                return visitDao.startRefusalProcedure(visitId, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public List<RefusalMedicineRecommendation>
    fetchRefusalMedicineRecommendations(String visitId, AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserHasRightsToTreat(user)
                && validator.isStringIdCorrect(visitId)) {
            try {
                return visitDao.fetchRefusalMedicineRecommendations(visitId, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AdmissionDepartmentVisit> fetchVisitToRefuse(AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserHasRightsToTreat(user)) {
            try {
                return visitDao.fetchVisitToRefuse(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean addRefusalMedicineRecommendation(String medicineId,
            String intakeInstruction, String visitId, AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserHasRightsToTreat(user)
                && validator.isStringIdCorrect(medicineId)
                && validator.isStringIdCorrect(visitId)) {
            try {
                return visitDao.addRefusalMedicineRecommendation(medicineId,
                        visitId, intakeInstruction, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean cancelRefusalMedicineRecommendation(String recommendId,
            AuthUser user) throws ServiceException {
        if (validator.isAuthUserHasRightsToTreat(user)
                && validator.isStringIdCorrect(recommendId)) {
            try {
                return visitDao.cancelRefusalMedicineRecommendation(recommendId,
                        user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public int formRefusalReference(String refusalRecommendations,
            String visitId, AuthUser user) throws ServiceException {
        if (validator.isAuthUserHasRightsToTreat(user)
                && validator.isStringIdCorrect(visitId)
                && (refusalRecommendations != null)) {
            try {
                return visitDao.formRefusalReference(refusalRecommendations,
                        visitId, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return -1;
    }
}

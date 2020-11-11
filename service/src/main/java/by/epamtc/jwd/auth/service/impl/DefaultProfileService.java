package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.ProfileDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.user_info.AllergicFoodReaction;
import by.epamtc.jwd.auth.model.user_info.AllergicMedicineReaction;
import by.epamtc.jwd.auth.model.user_info.AllergicReactionsInfo;
import by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase;
import by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.service.ProfileService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.validation.ProfileDataValidator;

import java.util.List;


public class DefaultProfileService implements ProfileService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private ProfileDao profileDao = daoFactory.getProfileDao();
    private ProfileDataValidator validator = new ProfileDataValidator();

    @Override
    public PatientInfo fetchPatientInfo(AuthUser authUser)
            throws ServiceException {
        if (validator.isAuthUserValidForProfileActivity(authUser)) {
            try {
                return profileDao.fetchPatientInfo(authUser);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean changePatientInfo(PatientInfo changingPatientInfo,
            AuthUser user) throws ServiceException {
        if (validator.isAuthUserValidForProfileActivity(user)
                && validator.isChangingPatientInfoValid(changingPatientInfo)) {
            try {
                return profileDao.changePatientInfo(changingPatientInfo, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public List<MedicalHistoryPermission> fetchMedicalHistoryPermissions(AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserValidForProfileActivity(user)) {
            try {
                return profileDao.fetchMedicalHistoryPermissions(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean cancelMedicalHistoryPermission(String permission,
            AuthUser user) throws ServiceException {
        if (validator.isAuthUserValidForProfileActivity(user)
                && permission.matches(RegistrationInfoPattern.DIGITS)) {
            try {
                return profileDao.cancelMedicalHistoryPermission(permission,
                        user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean addMedicalHistoryPermission(String recipientId, AuthUser user) throws ServiceException {
        if (validator.isAuthUserValidForProfileActivity(user)
                && recipientId.matches(RegistrationInfoPattern.DIGITS)) {
            try {
                return profileDao.addMedicalHistoryPermission(recipientId, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public AllergicReactionsInfo fetchAllergicReactionsInfo(AuthUser user) throws ServiceException {
        if (validator.isAuthUserValidForProfileActivity(user)) {
            try {
                return profileDao.fetchAllergicReactionsInfo(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean addAllergicFoodReaction(AllergicFoodReaction reaction,
            AuthUser user) throws ServiceException {
        if (validator.isAuthUserValidForProfileActivity(user)
                && reaction.getFoodTypeInfo()
                .matches(RegistrationInfoPattern.DIGITS)) {
            try {
                return profileDao.addAllergicFoodReaction(reaction, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean addAllergicMedicineReaction(AllergicMedicineReaction reaction,
            AuthUser user) throws ServiceException {
        if (validator.isAuthUserValidForProfileActivity(user)
                && reaction.getMedicineDescription().matches
                (RegistrationInfoPattern.DIGITS)) {
            try {
                return profileDao.addAllergicMedicineReaction(reaction, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public List<ExtremelyHazardousDiseaseCase>
    fetchCasesOfExtremelyHazardousDiseases(AuthUser user) throws ServiceException {
        if (validator.isAuthUserValidForProfileActivity(user)) {
            try {
                return profileDao.fetchCasesOfExtremelyHazardousDiseases(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean addExtremelyHazardousDisease(ExtremelyHazardousDiseaseCase disease, AuthUser user)
            throws ServiceException {
        if (validator.isAuthUserValidForProfileActivity(user)
                && disease.getDiseaseDescription().matches
                (RegistrationInfoPattern.DIGITS)) {
            try {
                return profileDao.addExtremelyHazardousDisease(disease, user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }
}

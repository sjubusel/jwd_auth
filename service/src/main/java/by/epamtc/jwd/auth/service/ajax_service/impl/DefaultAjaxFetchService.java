package by.epamtc.jwd.auth.service.ajax_service.impl;

import by.epamtc.jwd.auth.dao.ajax_dao.AjaxDaoFactory;
import by.epamtc.jwd.auth.dao.ajax_dao.AjaxFetchDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxArea;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxCountry;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxDisease;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxFoodType;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxHazardousDisease;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxMedicine;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxMedicineType;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxPerson;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxRegion;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxRoad;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxSettlement;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.service.ajax_service.AjaxFetchService;
import by.epamtc.jwd.auth.service.ajax_service.validation.AjaxValidator;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.util.List;

public class DefaultAjaxFetchService implements AjaxFetchService {
    private AjaxDaoFactory ajaxDaoFactory = AjaxDaoFactory.getInstance();
    private AjaxFetchDao ajaxFetchDao = ajaxDaoFactory.getAjaxFetchDao();
    private AjaxValidator ajaxValidator = new AjaxValidator();


    @Override
    public List<AjaxCountry> fetchCountries(String countryElement)
            throws ServiceException {
        if (ajaxValidator.isFetchInputValid(countryElement)) {
            try {
                return ajaxFetchDao.fetchCountries(countryElement);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AjaxRegion> fetchRegions(String countryId, String regionInput)
            throws ServiceException {
        if (ajaxValidator.isInputValidForDependentFetch(countryId, regionInput)) {
            int countryNumber = Integer.parseInt(countryId);
            try {
                return ajaxFetchDao.fetchRegions(countryNumber, regionInput);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AjaxArea> fetchAreas(String regionId, String areaInput)
            throws ServiceException {
        if (ajaxValidator.isInputValidForDependentFetch(regionId, areaInput)) {
            int regionNumber = Integer.parseInt(regionId);
            try {
                return ajaxFetchDao.fetchAreas(regionNumber, areaInput);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AjaxSettlement> fetchSettlements(String areaId,
            String settlementInput) throws ServiceException {
        if (ajaxValidator.isInputValidForDependentFetch(areaId, settlementInput)) {
            int areaNumber = Integer.parseInt(areaId);
            try {
                return ajaxFetchDao.fetchSettlements(areaNumber, settlementInput);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AjaxRoad> fetchRoads(String settlementId, String roadInput)
            throws ServiceException {
        if (ajaxValidator.isInputValidForDependentFetch(settlementId,
                roadInput)) {
            int settlementNumber = Integer.parseInt(settlementId);
            try {
                return ajaxFetchDao.fetchRoads(settlementNumber, roadInput);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AjaxPerson> fetchPersons(String recipientPerson)
            throws ServiceException {
        if (ajaxValidator.isFetchInputValid(recipientPerson)) {
            try {
                return ajaxFetchDao.fetchPersons(recipientPerson);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AjaxFoodType> fetchFoodTypes(String foodTypePart)
            throws ServiceException {
        if (foodTypePart.matches(RegistrationInfoPattern.ROOM)) {
            try {
                return ajaxFetchDao.fetchFoodTypes(foodTypePart);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AjaxMedicineType> fetchMedicineTypes(String medicineTypePart) throws ServiceException {
        if (medicineTypePart.matches(RegistrationInfoPattern.ANY_LATIN_NAME)) {
            try {
                return ajaxFetchDao.fetchMedicineTypes(medicineTypePart);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AjaxHazardousDisease> fetchExtremelyHazardousDiseases(String
            diseasePart) throws ServiceException {
        if (diseasePart.matches(RegistrationInfoPattern.ROOM)) {
            try {
                return ajaxFetchDao.fetchExtremelyHazardousDiseases(diseasePart);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AjaxDisease> fetchDiseases(String diseasePart)
            throws ServiceException {
        if (ajaxValidator.isFetchInputValid(diseasePart)) {
            try {
                return ajaxFetchDao.fetchDiseases(diseasePart);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public List<AjaxMedicine> fetchMedicines(String medicinePart)
            throws ServiceException {
        if (ajaxValidator.isFetchInputValid(medicinePart)) {
            try {
                return ajaxFetchDao.fetchMedicines(medicinePart);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public int fetchAmountOfPagesOfReferences(AuthUser user)
            throws ServiceException {
        if (user.getRole().getRoleId() >= AppConstant
                .STAFF_WHICH_CAN_VIEW_MEDICINE_DOCUMENTS) {
            try {
                return ajaxFetchDao.fetchAmountOfPagesOfReferences(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return 0;
    }

    @Override
    public int fetchAmountOfPagesOfReferencesForPatient(AuthUser user)
            throws ServiceException {
        if (user.getUserId() > 0) {
            try {
                return ajaxFetchDao.fetchAmountOfPagesOfReferencesForPatient(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return 0;
    }
}

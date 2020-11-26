package by.epamtc.jwd.auth.service.ajax_service;

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
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.util.List;

public interface AjaxFetchService {
    List<AjaxCountry> fetchCountries(String countryElement)
            throws ServiceException;

    List<AjaxRegion> fetchRegions(String countryId, String regionInput)
            throws ServiceException;

    List<AjaxArea> fetchAreas(String regionId, String areaInput)
            throws ServiceException;

    List<AjaxSettlement> fetchSettlements(String areaId, String settlementInput)
            throws ServiceException;

    List<AjaxRoad> fetchRoads(String settlementId, String roadInput)
            throws ServiceException;

    List<AjaxPerson> fetchPersons(String recipientPerson)
            throws ServiceException;

    List<AjaxFoodType> fetchFoodTypes(String foodTypePart)
            throws ServiceException;

    List<AjaxMedicineType> fetchMedicineTypes(String medicineTypePart)
            throws ServiceException;

    List<AjaxHazardousDisease> fetchExtremelyHazardousDiseases(String diseasePart)
            throws ServiceException;

    List<AjaxDisease> fetchDiseases(String diseasePart)
            throws ServiceException;

    List<AjaxMedicine> fetchMedicines(String medicinePart)
            throws ServiceException;

    int fetchAmountOfPagesOfReferences(AuthUser user) throws ServiceException;

    int fetchAmountOfPagesOfReferencesForPatient(AuthUser user) throws ServiceException;
}

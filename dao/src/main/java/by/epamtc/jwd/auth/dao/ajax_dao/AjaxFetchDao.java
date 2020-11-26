package by.epamtc.jwd.auth.dao.ajax_dao;

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

import java.util.List;

public interface AjaxFetchDao {
    List<AjaxCountry> fetchCountries(String countryPart) throws DaoException;

    List<AjaxRegion> fetchRegions(int countryNumber, String regionInput)
            throws DaoException;

    List<AjaxArea> fetchAreas(int regionNumber, String areaInput)
            throws DaoException;

    List<AjaxSettlement> fetchSettlements(int areaNumber,
            String settlementInput) throws DaoException;

    List<AjaxRoad> fetchRoads(int settlementNumber, String roadInput)
            throws DaoException;

    List<AjaxPerson> fetchPersons(String recipientPerson) throws DaoException;

    List<AjaxFoodType> fetchFoodTypes(String foodTypePart) throws DaoException;

    List<AjaxMedicineType> fetchMedicineTypes(String medicineTypePart)
            throws DaoException;

    List<AjaxHazardousDisease> fetchExtremelyHazardousDiseases(String diseasePart)
            throws DaoException;

    List<AjaxDisease> fetchDiseases(String diseasePart) throws DaoException;

    List<AjaxMedicine> fetchMedicines(String medicinePart)
            throws DaoException;

    int fetchAmountOfPagesOfReferences(AuthUser user) throws DaoException;

    int fetchAmountOfPagesOfReferencesForPatient(AuthUser user)
            throws DaoException;
}

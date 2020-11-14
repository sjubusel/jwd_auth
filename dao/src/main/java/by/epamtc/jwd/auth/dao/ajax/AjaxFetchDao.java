package by.epamtc.jwd.auth.dao.ajax;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.ajax.*;

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
}

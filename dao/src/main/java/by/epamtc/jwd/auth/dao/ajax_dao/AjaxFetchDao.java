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

/**
 * An interface which communicates with a data source (MySql) and retrieves
 * data for ajax-requests.
 * <p>
 * An interface which is created in order to retrieve specific auxiliary data
 * for ajax-requests.
 *
 * @see by.epamtc.jwd.auth.dao.ajax_dao.impl.DefaultAjaxFetchDao main implementation at the present
 */
public interface AjaxFetchDao {
    /**
     * a method which retrieves a list of objects which contains shortened
     * information about countries, which are stored in the source (database).
     *
     * @param countryPart a String, which represents a part of a country,
     *                    which is to be found
     * @return a List object, which contains AjaxCountry objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<AjaxCountry> fetchCountries(String countryPart) throws DaoException;

    /**
     * a method which retrieves a list of objects which contains shortened
     * information about regions (according to international practice it is
     * a administrative and territorial division of the 1st level),
     * which are stored in the source (database) and
     * which are assigned to a specific country.
     *
     * @param countryNumber an int-primitive type, which represents id of
     *                      a country in the source
     * @param regionInput   a String, which represents a part of a region,
     *                      which is to be found
     * @return a List object, which contains AjaxRegion objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<AjaxRegion> fetchRegions(int countryNumber, String regionInput)
            throws DaoException;

    /**
     * a method which retrieves a list of objects which contains shortened
     * information about area(according to international practice it is
     * a administrative and territorial division of the 2nd level),
     * which are stored in the source (database) and
     * which are assigned to a specific region.
     *
     * @param regionNumber an int-primitive type, which represents id of a region
     *                     in the source
     * @param areaInput    a String, which represents a part of an area which is
     *                     to be found
     * @return a List object, which contains AjaxArea objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<AjaxArea> fetchAreas(int regionNumber, String areaInput)
            throws DaoException;

    /**
     * a method which retrieves a list of objects which contains shortened
     * information about settlements,
     * which are stored in the source (database) and
     * which are assigned to a specific area.
     *
     * @param areaNumber      an int-primitive type, which represents id of
     *                        an area in the source
     * @param settlementInput a String, which represents a part of an settlement
     *                        which is to be found
     * @return a list object, which contains AjaxSettlement objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<AjaxSettlement> fetchSettlements(int areaNumber,
            String settlementInput) throws DaoException;

    /**
     * a method which retrieves a list of objects which contains shortened
     * information about roads, which are stored in the source and which
     * are assigned to a specific settlement
     *
     * @param settlementNumber an int-primitive type, which represents id of
     *                         a settlement in the source
     * @param roadInput        a String, which represents a part of a road
     *                         which is to be found
     * @return a list object, which contains AjaxRoad objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection
     *                      from ConnectionPool will occur
     */
    List<AjaxRoad> fetchRoads(int settlementNumber, String roadInput)
            throws DaoException;

    /**
     * a method which retrieves a list of objects which contains shortened
     * information about persons which is stored in the source
     *
     * @param recipientPerson a String, which represents a part of a persons's
     *                        data
     * @return a list of AjaxPerson objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection
     *                      from ConnectionPool will occur
     */
    List<AjaxPerson> fetchPersons(String recipientPerson) throws DaoException;

    /**
     * a method which retrieves types of food in order to add an allergic
     * reaction to food
     *
     * @param foodTypePart a String, which contains a part of a food type,
     *                     which is searched for
     * @return a list of AjaxFoodType Objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection
     *                      from ConnectionPool will occur
     */
    List<AjaxFoodType> fetchFoodTypes(String foodTypePart) throws DaoException;

    /**
     * a method which retrieves types of medicines in order to add an allergic
     * reaction to a medicine
     *
     * @param medicineTypePart a String, which contains a part of a medicine,
     *                         which is searched for
     * @return a list of AjaxFoodType Objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection
     *                      from ConnectionPool will occur
     */

    List<AjaxMedicineType> fetchMedicineTypes(String medicineTypePart)
            throws DaoException;

    /**
     * a method which retrieves extremely hazardous diseases (HIV, syphilis,
     * etc) in order to add a case of extremely hazardous diseases
     *
     * @param diseasePart a String, which contains a part of a name of
     *                    extremely hazardous disease, which is to be found
     * @return a list of AjaxHazardousDisease objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection
     *                      from ConnectionPool will occur
     */
    List<AjaxHazardousDisease> fetchExtremelyHazardousDiseases(String diseasePart)
            throws DaoException;

    /**
     * a method which retrieves all stored in the source diseases
     * by a search word
     *
     * @param diseasePart a String, which contains a part of a name of a disease,
     *                    which is to be found
     * @return a list of AjaxDisease objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection
     *                      from ConnectionPool will occur
     */
    List<AjaxDisease> fetchDiseases(String diseasePart) throws DaoException;

    /**
     * a method which retrieves all stored in the source medicines
     * by a search word
     *
     * @param medicinePart a String, which contains a part of a name of
     *                     a medicine, which is searched for
     * @return a list of AjaxMedicine objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection
     *                      from ConnectionPool will occur
     */
    List<AjaxMedicine> fetchMedicines(String medicinePart)
            throws DaoException;

    /**
     * a method which retrieves an amount of pages of refusal references for
     * a doctor from an admitting department
     *
     * @param user an AuthUser object, which contains information about a user,
     *             who performs this action
     * @return an int-primitive type, which value shows an amount of pages for
     * pagination, while viewing refusal references issued by this doctor
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection
     *                      from ConnectionPool will occur
     */
    int fetchAmountOfPagesOfReferences(AuthUser user) throws DaoException;

    /**
     * a method which retrieves an amount of pages of refusal references for
     * a patient whom they are prescribed for
     *
     * @param user an AuthUser object, which contains information about a user,
     *             who performs this action
     * @return an int-primitive type, which value shows an amount of pages
     * for pagination while viewing refusal references concerning this patient
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection
     *                      from ConnectionPool will occur
     */
    int fetchAmountOfPagesOfReferencesForPatient(AuthUser user)
            throws DaoException;
}

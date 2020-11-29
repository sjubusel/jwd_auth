package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.user_info.AllergicFoodReaction;
import by.epamtc.jwd.auth.model.user_info.AllergicMedicineReaction;
import by.epamtc.jwd.auth.model.user_info.AllergicReactionsInfo;
import by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase;
import by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

import java.util.List;

/**
 * an interface that established contracts for profile-connected activity
 * performed a user of the current information system entered as a patient
 * while "surfing" in profile-subsystem
 */
public interface ProfileDao {
    /**
     * a method which fetches data from the source and generates from them
     * by.epamtc.jwd.auth.model.user_info.PatientInfo, which contains
     * information about person's identification data and permanent data
     * about state of health
     *
     * @param authUser an by.epamtc.jwd.auth.model.auth_info.AuthUser object
     *                 which identifies a person whom an aforementioned
     *                 fetched object is generated for.
     * @return by.epamtc.jwd.auth.model.auth_info.AuthUser which contains
     * information about person's identification data and permanent data
     * about state of health
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    PatientInfo fetchPatientInfo(AuthUser authUser) throws DaoException;

    /**
     * a method which changes information about person's identification data
     * and permanent data about state of health using
     * by.epamtc.jwd.auth.model.user_info.PatientInfo. If a field of this object
     * a corresponding data will be changed in he source. if this field is null
     * no changes to corresponding data won't be changed
     *
     * @param changingPatientInfo by.epamtc.jwd.auth.model.user_info.PatientInfo
     *                            which contains information which will replace
     *                            corresponding data in the source
     * @param user                an by.epamtc.jwd.auth.model.auth_info.AuthUser object
     *                            which identifies a person whom an aforementioned
     *                            object changes data for.
     * @return true if changes are fully applied according to
     * <code>PatientInfo changingPatientInfo</code> argument
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean changePatientInfo(PatientInfo changingPatientInfo, AuthUser user)
            throws DaoException;

    /**
     * a method that receives information from the source and generates from
     * it an by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission object
     * which contains information about cases of granting a permission to user's
     * medical history to another person
     *
     * @param user an AuthUser object, which identifies a person who grants
     *             aforementioned permissions
     * @return a list object, which contains by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission
     * which contains information about cases of granting a permission to user's
     * medical history to another person
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<MedicalHistoryPermission> fetchMedicalHistoryPermissions(AuthUser user)
            throws DaoException;

    /**
     * a method that cancels a permission to medical history
     *
     * @param permissionId id of an aforementioned permission
     * @param user         an AuthUser object which identifies a patient who granted
     *                     a permission with id from <code>String permissionId</code>
     * @return true if an aforementioned permission is successfully cancelled
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean cancelMedicalHistoryPermission(String permissionId, AuthUser user)
            throws DaoException;

    /**
     * a method that adds a permission to medical history
     *
     * @param recipientId a String which contains id of a person whom the
     *                    permission is granted to
     * @param user        an AuthUser object which identifies a person whom the
     *                    permission is granted to
     * @return true if an aforementioned permission is successfully added
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean addMedicalHistoryPermission(String recipientId, AuthUser user)
            throws DaoException;

    /**
     * a method which generates information containing patient's data about
     * his "allergic" cases
     *
     * @param user an AuthUser object which identifies a person whose cases
     *             are fetched
     * @return by.epamtc.jwd.auth.model.user_info.AllergicReactionsInfo object
     * which contains patient's data about his "allergic" cases
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    AllergicReactionsInfo fetchAllergicReactionsInfo(AuthUser user)
            throws DaoException;

    /**
     * a method which adds an allergic reaction to food
     *
     * @param reaction a by.epamtc.jwd.auth.model.user_info.AllergicFoodReaction
     *                 object that describes an allergic reaction to food
     * @param user     an AuthUser object which identifies a person whose allergic
     *                 reaction is to be added
     * @return true if a aforementioned reaction to food is successfully added
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean addAllergicFoodReaction(AllergicFoodReaction reaction,
            AuthUser user) throws DaoException;

    /**
     * a method which adds an allergic reaction to a medicine
     *
     * @param reaction a by.epamtc.jwd.auth.model.user_info.AllergicMedicineReaction
     *                 object that describes an allergic reaction to a medicine
     * @param user     an AuthUser object which identifies a person whose allergic
     *                 reaction is to be added
     * @return true if a aforementioned reaction to a medicine is successfully added
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean addAllergicMedicineReaction(AllergicMedicineReaction reaction,
            AuthUser user) throws DaoException;

    /**
     * a method that fetches cases of extremely hazardous diseases , which belong
     * to a patient, who initiates call of this method. These cases are used
     * in order to protect medical staff from falling ill with aforementioned
     * diseases
     *
     * @param user an AuthUser object which identifies a patient
     *             whose aforementioned cases are fetched
     * @return a List object of by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase
     * that describe aforementioned cases
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<ExtremelyHazardousDiseaseCase> fetchCasesOfExtremelyHazardousDiseases
    (AuthUser user) throws DaoException;

    /**
     * a method which adds a case of a extremely hazardous disease
     *
     * @param disease by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase
     *                object which contains information about development of
     *                an extremely hazardous disease
     * @param user    an AuthUser which identifies a patient whom case of an
     *                extremely hazardous disease is to be added
     * @return true if a case of an extremely hazardous disease is successfully
     * added
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean addExtremelyHazardousDisease(ExtremelyHazardousDiseaseCase disease,
            AuthUser user) throws DaoException;
}

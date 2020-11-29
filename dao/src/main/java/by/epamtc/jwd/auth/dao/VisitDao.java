package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.RefusalMedicineRecommendation;
import by.epamtc.jwd.auth.model.visit_info.RefusalReference;

import java.util.List;

/**
 * an interface that established contracts for
 * visit-to-admitting-department-connected activity mainly performed by medical
 * staff
 */
public interface VisitDao {
    /**
     * a method which preforms registration of a visit to the admitting
     * department of a hospital
     *
     * @param hospVisit a by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     *                  object that possesses all necessary information about
     *                  a visit to the admitting department of a hospital
     * @return true if visit information is successfully added
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean registerVisit(AdmissionDepartmentVisit hospVisit)
            throws DaoException;

    /**
     * a method which receives visit to the admitting department which are not
     * taken for treatment by a doctor
     *
     * @param user an AuthUser object that identifies a person, who is a doctor
     *             of the admitting department and is to take control on one of
     *             visits
     * @return a List object which contains by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     * objects that are not taken for treatment by a doctor
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<AdmissionDepartmentVisit> fetchNewVisits(AuthUser user)
            throws DaoException;

    /**
     * a method which accepts a patient for treatment during a visit. The call of
     * this method is initiated by a user with rights of doctor of the
     * admitting department or higher
     *
     * @param visitId a String which contains id of a visit to the admitting
     *                department. This visit is to be accepted for treatment
     * @param user    an AuthUser object, which identifies a member of hospital
     *                staff who is to treat a patient
     * @return true if treatment of a patient is successfully accepted
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean acceptPatientForTreatment(String visitId, AuthUser user)
            throws DaoException;

    /**
     * a method which visits to the admitting department which are on control of
     * a member of hospital staff if the decision on them is not made
     *
     * @param user an AuthUser object which identifies a member of hospital
     *             staff whom controlled visit are fetched by
     * @return a List object with by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<AdmissionDepartmentVisit> fetchControlledVisits(AuthUser user)
            throws DaoException;

    /**
     * a method which fetches full infornation about a visit to the admitting
     * department
     *
     * @param visitId a String that contains id of a visit to the admitting
     *                department which is to be fetched
     * @return a by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     * object that contains full information about a aforementioned visit
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    AdmissionDepartmentVisit fetchFullAdmissionDepartmentVisit(String visitId)
            throws DaoException;

    /**
     * a method which fetches diagnoses established during all visits to
     * the admitting department and hospitalization from the source. This
     * information is mainly used in order to quickly get familiar with a
     * patient by doctors
     *
     * @param patientId an int value, which is id of a patient whose diagnoses
     *                  are to be fetched
     * @return a List object with by.epamtc.jwd.auth.model.med_info.Diagnosis
     * objects that contain  diagnoses established during all visits to
     * the admitting department and hospitalization from the source
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<Diagnosis> fetchInnerHospitalDiagnoses(int patientId)
            throws DaoException;

    /**
     * a method that fetches both executed and unexecuted prescriptions of
     * a medicine which are established during a current visit to the
     * admitting department
     *
     * @param visitId a String which contains id of an aforementioned visit
     * @return a List object with by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     * objects which possess information about aforementioned prescriptions
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<MedicinePrescription> fetchVisitMedicinePrescriptions(String visitId)
            throws DaoException;

    /**
     * a method that fetches both executed and unexecuted prescriptions
     * which are established during a current visit to the
     * admitting department
     *
     * @param visitId a String which contains id of an aforementioned visit
     * @return a List object with by.epamtc.jwd.auth.model.med_info.Prescription
     * objects which possess information about aforementioned prescriptions
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<Prescription> fetchVisitPrescriptions(String visitId)
            throws DaoException;

    /**
     * a method which changes patient's complaints filled mainly by a doctor
     * during a visit to the admitting department
     *
     * @param complaints a String, which represents patient's complaints
     * @param visitId    a String, which contains id of a visit to the admitting
     *                   department
     * @param user       an AuthUser object which identifies a member of staff who
     *                   changes complaints
     * @return true if complaints are successfully changed
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean changeComplaints(String complaints, String visitId, AuthUser user)
            throws DaoException;

    /**
     * a method which establishes a diagnosis during a visit to the admitting
     * department
     *
     * @param diagnosis  by.epamtc.jwd.auth.model.med_info.Diagnosis object that
     *                   contains information about a newly diagnosed disease
     * @param visitStrId a String that contains id of an aforementioned visit
     * @param user       an AuthUser object that identifies a member of staff who
     *                   establishes a diagnosis
     * @return true if the diagnosis is successfully established
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean establishDiagnosis(Diagnosis diagnosis, String visitStrId,
            AuthUser user) throws DaoException;

    /**
     * a method which establishes a prescription of a medicine during a visit to
     * admitting department
     *
     * @param prescription by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     *                     object that contains information about a prescription
     *                     of a medicine
     * @param user         an AuthUser that identifies a member of staff who
     *                     establishes a prescription of a medicine
     * @return true if a prescription of a medicine is successfully established
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean establishMedicinePrescription(MedicinePrescription prescription,
            AuthUser user) throws DaoException;

    /**
     * a method which establishes a prescription during a visit to the
     * admitting department
     *
     * @param prescription by.epamtc.jwd.auth.model.med_info.Prescription
     *                     object that contains information about a prescription
     *                     of a medicine
     * @param user         an AuthUser that identifies a member of staff who
     *                     establishes a prescription of a medicine
     * @return true if a prescription of a medicine is successfully established
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean establishPrescription(Prescription prescription, AuthUser user)
            throws DaoException;

    /**
     * a method which cancels a prescription during a visit to the admitting
     * department
     *
     * @param prescriptionId a String which contains id of an aforementioned
     *                       prescription
     * @param user           an AuthUser object that identifies a member staff
     *                       who cancels an aforementioned prescription
     * @return true if a prescription is successfully canceled
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean cancelPrescription(String prescriptionId, AuthUser user)
            throws DaoException;

    /**
     * a method which cancels a prescription of a medicine during a visit to
     * the admitting department
     *
     * @param medPrescriptionId a String which contains id of an aforementioned
     *                          prescription
     * @param user              an AuthUser object that identifies a member staff
     *                          who cancels an aforementioned prescription
     * @return true if a prescription is successfully canceled
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean cancelMedicinePrescription(String medPrescriptionId, AuthUser user)
            throws DaoException;

    /**
     * a method that fetches by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     * that contain information about all newly established prescriptions
     * of medicines that are to be executed
     *
     * @return a List object that contains aforementioned medicine prescriptions
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<MedicinePrescription> fetchAllNewMedicinePrescriptions()
            throws DaoException;

    /**
     * a method which fetches a by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     * object that contains full information about a prescription of a medicine
     * using id of the prescription
     *
     * @param medPrescriptionId a String that contains id of a medicine
     *                          prescription
     * @return by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     * object that contains full information about a prescription of a medicine
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    MedicinePrescription fetchVisitMedPrescriptionById(String medPrescriptionId)
            throws DaoException;

    /**
     * a method which records information about an execution of a medicine
     * prescription
     *
     * @param medPrescriptionId a String that contains id of a medicine
     *                          prescription
     * @param executionResult   a String that contains a result of execution of
     *                          a medicine prescription
     * @param user              an AuthUser object that identifies a member of
     *                          a staff who
     *                          executes a prescription of a medicine
     * @return true if a prescription of a medicine is successfully executed
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean executeMedPrescription(String medPrescriptionId,
            String executionResult, AuthUser user) throws DaoException;

    /**
     * a method that fetches by.epamtc.jwd.auth.model.med_info.Prescription
     * that contain information about all newly established prescriptions
     * that are to be executed
     *
     * @return a List object that contains aforementioned prescriptions
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<Prescription> fetchAllNewNonMedicinePrescriptions()
            throws DaoException;

    /**
     * a method which accepts putting on control of a prescription by a member
     * of staff
     *
     * @param prescriptionId a String which contains id of a prescription
     *                       that is to be put on control
     * @param user           an AuthUser that identifies a member of staff who puts
     *                       an aforementioned prescription on control
     * @return true if a prescription is successfully put on control
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean acceptPrescriptionOnControl(String prescriptionId, AuthUser user)
            throws DaoException;

    /**
     * a method that fetches by.epamtc.jwd.auth.model.med_info.Prescription
     * objects that contains information about prescriptions that are on control
     * but not executed
     *
     * @param user an AuthUser object that identifies a member of staff
     *             whom control the prescription is put on
     * @return a List object with by.epamtc.jwd.auth.model.med_info.Prescription
     * objects that contains information about prescriptions that are on control
     * but not executed
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<Prescription> fetchControlledVisitPrescriptions(AuthUser user)
            throws DaoException;

    /**
     * a method that fetches a prescription during a visit to the admitting
     * department by prescription's id
     *
     * @param prescriptionId a String that contains id of a prescription
     *                       which is to be fetched
     * @return by.epamtc.jwd.auth.model.med_info.Prescription object that
     * contains information about a prescription during a visit to the admitting
     * department by prescription's id
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    Prescription fetchVisitPrescriptionById(String prescriptionId)
            throws DaoException;

    /**
     * a method which records information about an execution of a  prescription
     *
     * @param prescriptionId  a String that contains id of a prescription
     * @param executionResult a String that contains a result of execution of
     *                        a  prescription
     * @param user            an AuthUser object that identifies a member of
     *                        a staff who executes a prescription
     * @return true if a prescription is successfully executed
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean executePrescription(String prescriptionId, String executionResult,
            AuthUser user) throws DaoException;

    /**
     * a method which marks that a doctor made a decision to refuse to
     * hospitalize a patient during a visit to the admitting department
     *
     * @param visitId a String that contains id of a visit to the admitting
     *                department which decision to refuse to hospitalize is
     *                made on
     * @param user    an AuthUser object which identifies a member of staff who
     *                made a decision
     * @return true if a decision to refuse to hospitalize is successfully made
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean startRefusalProcedure(String visitId, AuthUser user)
            throws DaoException;

    /**
     * a method which fetches recommendations of medicines established during
     * a visit to admitting department which decision to refuse to hospitalize
     * is made on
     *
     * @param visitId a String that contains id of a visit to admitting
     *                department which decision to refuse to hospitalize
     *                is made on
     * @param user    an AuthUser
     * @return a List object with by.epamtc.jwd.auth.model.visit_info.RefusalMedicineRecommendation
     * objects that contain aforementioned recommendations
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<RefusalMedicineRecommendation> fetchRefusalMedicineRecommendations(
            String visitId, AuthUser user) throws DaoException;

    /**
     * a method which fetches visits to the admitting department  which
     * decision to refuse to hospitalize is made on by a doctor of the
     * admitting department,  but a medical refusal
     * reference in compliance with healthcare legislation of the Republic of
     * Belarus is not registered
     *
     * @param user an AuthUser object that identifies a doctor of the admitting
     *             department who made a decision to refuse to hospitalize
     *             a patient, but didn't registered an aforementioned reference
     * @return a list object with by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     * object that contains aforementioned visits
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<AdmissionDepartmentVisit> fetchVisitsToRefuse(AuthUser user)
            throws DaoException;

    /**
     * a method which adds a recommendation of medicines established during
     * a visit to admitting department which decision to refuse to hospitalize
     * is made on
     *
     * @param medicineId        a String that contains id of a medicine which is to be
     *                          recommended to take
     * @param visitId           a String that contains id of a visit to the admitting
     *                          department department which decision to refuse
     *                          to hospitalize is made on
     * @param intakeInstruction a String that contains description of how to
     *                          take a recommended medicine
     * @param user              an AuthUser object which identifies a member
     *                          of staff who make a recommendation of a medicine
     * @return true if a recommendation is successfully added
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean addRefusalMedicineRecommendation(String medicineId, String visitId,
            String intakeInstruction, AuthUser user) throws DaoException;

    /**
     * a method which cancels a recommendation of medicines established during
     * a visit to admitting department which decision to refuse to hospitalize
     * is made on
     *
     * @param recommendId a String that contains id of an aforementioned
     *                    recommendation
     * @param user        an AuthUser object which identifies a member
     *                    of staff who cancel a recommendation of a medicine
     * @return true if a recommendation is successfully cancelled
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean cancelRefusalMedicineRecommendation(String recommendId,
            AuthUser user) throws DaoException;

    /**
     * a method which forms and register a medical reference about stay at the
     * admitting department in compliance with healthcare legislation of the
     * Republic of Belarus
     *
     * @param refusalRecommendations a String which contains non-medicine
     *                               recommendations of a doctor after refusal to
     *                               hopitalize
     * @param visitId                a String which contains id of a visit to
     *                               admitting department which decision to
     *                               refuse to hospitalize is made on
     * @param user                   an AuthUser object that identifies a doctor
     *                               who registers an aforementioned reference
     * @return an int value, which is id of newly created
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    int formRefusalReference(String refusalRecommendations, String visitId,
            AuthUser user) throws DaoException;

    /**
     * a method which fetches medical references about stay at the admitting
     * department (refusal references) formed by a establishing them doctor
     *
     * @param pageNumber a number of a page with refusal references which are
     *                   established by an aforementioned doctor
     * @param user       au AuthUser which conform to an aforementioned doctor
     * @return a List object with by.epamtc.jwd.auth.model.visit_info.RefusalReference
     * that contain shortened information about aforementioned references
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<RefusalReference> fetchRefusalReferences(String pageNumber, AuthUser user)
            throws DaoException;

    /**
     * a method which fetches a medical reference about stay at the admitting
     * department (refusal reference in detail)
     *
     * @param referenceId a String that contains id of an aforementioned
     *                    reference
     * @param user        au AuthUser object which identifies a user who
     *                    initiated calling of this method
     * @return by.epamtc.jwd.auth.model.visit_info.RefusalReference object which
     * contains information that makes up a medical reference about stay at
     * the admitting department
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    RefusalReference fetchDetailedRefusalReference(String referenceId,
            AuthUser user) throws DaoException;
}

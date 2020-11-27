package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.RefusalReference;

import java.util.List;

/**
 * an interface that established contracts for patient-connected activity
 * performed a user of the current information system entered as a patient
 * while receiving medical aid in the hospital
 */
public interface PatientDao {
    /**
     * a method which returns information about a current visit to the admitting
     * department of the hospital if it exists
     *
     * @param user au AuthUser object that represents a user which signed in
     *             as a patient and preforms this action
     * @return a bean "AdmissionDepartmentVisit" which contains full information
     * about a visit to the admitting department of the hospital
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    AdmissionDepartmentVisit fetchFullVisitIfExist(AuthUser user)
            throws DaoException;

    /**
     * a method that fetches a prescriptions of medicines which are
     * newly established, but still not executed
     *
     * @param user an AuthUSer which represents a current user which entered
     *             as a patient and for whom aforementioned prescriptions
     *             established for
     * @return a List object which contains MedicinePrescription objects
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<MedicinePrescription> fetchAllNewMedicinePrescriptions(AuthUser user)
            throws DaoException;

    /**
     * a method that fetches id of a patient when we have possess only id of
     * a medicine prescription
     *
     * @param prescriptionId a String, which matches integer and is used for
     *                       finding of id of a patient
     * @return an int value, which is id of a patient
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    int fetchPatientIdByMedicinePrescriptionId(String prescriptionId)
            throws DaoException;

    /**
     * a method which is used to cancel a medicine prescription by a patient
     *
     * @param prescriptionId          a String, which is a integer that represents
     *                                id of a medicine prescription
     * @param disagreementDescription a reason for cancellation of a prescription
     * @return true if prescription cancelled
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean disagreeWithMedicinePrescription(String prescriptionId,
            String disagreementDescription) throws DaoException;

    /**
     * a method that fetches newly prescriptions (not executed)
     *
     * @param user an AuthUser which represents a patient whom
     *             these prescriptions ar establish for
     * @return a List object that consists of newly established prescription
     * for a patient who initialize calling of this method
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<Prescription> fetchAllNewPrescriptions(AuthUser user)
            throws DaoException;

    /**
     * a method that retrieves id of a patient if we know id of a prescription
     *
     * @param prescriptionId a String, which is a integer that represents
     *                       id of a prescription
     * @return an int value, which is id of a patient
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    int fetchPatientIdByPrescriptionId(String prescriptionId)
            throws DaoException;

    /**
     * a method which is used to cancel a medicine prescription by a patient
     *
     * @param prescriptionId          a String, which is a integer that represents
     *                                id of a prescription
     * @param disagreementDescription a reason for cancellation of a prescription
     * @return true if prescription cancelled
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean disagreeWithPrescription(String prescriptionId,
            String disagreementDescription) throws DaoException;

    /**
     * a method which fetches id of a patient if we know id of visit to the
     * admitting department
     *
     * @param visitId a String, which contains id of an aforementioned visit
     * @return an int value, which is id of a patient
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    int fetchPatientIdByVisitId(int visitId) throws DaoException;

    /**
     * a method which fetched diagnoses, which are establish for a patient
     * during a visit to the admitting department
     *
     * @param visitId a String, which contains id of an aforementioned visit
     * @return a List object that consists of aforementioned Diagnoses
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<Diagnosis> fetchDiagnosesForPatientDuringVisit(int visitId)
            throws DaoException;

    /**
     * a method that fetches prescriptions of medicines which are already
     * completed furing a visit to the admitting department
     *
     * @param visitId a String which contains id of an aforementioned visit
     * @return a List object containing aforementioned prescriptions if they
     * exist
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<MedicinePrescription> fetchMedPrescriptionsFinishedDuringVisit(int visitId)
            throws DaoException;

    /**
     * a method which fetches prescriptions which are already completed during
     * a visit to the admitting department
     *
     * @param visitId a String which contains if of an aforementioned visit
     * @return a List object containing aforementioned prescriptions if they
     * exist
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<Prescription> fetchPrescriptionsFinishedDuringVisit(int visitId)
            throws DaoException;

    /**
     * a method which fetches refusal references which are established when
     * a doctor from the admitting department
     *
     * @param pageNumber a number of a page with refusal references which are
     *                   established for a patient who initialize current action
     * @param user       au AuthUser which conform to an aforementioned patient
     * @return a List object with aforementioned references
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    List<RefusalReference> fetchRefusalReferences(String pageNumber,
            AuthUser user) throws DaoException;

    /**
     * a method which finds id of patient by id of a refusal reference
     *
     * @param referenceId a String which contains id of a refusal reference
     * @return an int value, which is id of a patient
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    int fetchPatientIdByReferenceId(String referenceId) throws DaoException;

    /**
     * a method that fetches a medical reference after a refusal to hospitalize
     * by id of such a reference. This medical reference contains all necessary
     * information in compliance with legislation of the Republic of Belarus
     *
     * @param referenceId a String which contains id of an aforementioned
     *                    reference
     * @return a bean RefusalReference which contains information that will make
     * up a medical reference after a refusal to hospitalize in compliance
     * with healthcare legislation of the Republic of Belarus
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    RefusalReference fetchDetailedRefusalReference(String referenceId)
            throws DaoException;
}

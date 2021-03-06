package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.user_info.AllergicFoodReaction;
import by.epamtc.jwd.auth.model.user_info.AllergicMedicineReaction;
import by.epamtc.jwd.auth.model.user_info.AllergicReactionsInfo;
import by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase;
import by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.util.List;

public interface ProfileService {
    PatientInfo fetchPatientInfo(AuthUser authUser) throws ServiceException;

    boolean changePatientInfo(PatientInfo changingPatientInfo, AuthUser user)
            throws ServiceException;

    List<MedicalHistoryPermission> fetchMedicalHistoryPermissions(AuthUser user)
            throws ServiceException;

    boolean cancelMedicalHistoryPermission(String permission, AuthUser user)
            throws ServiceException;

    boolean addMedicalHistoryPermission(String recipientId, AuthUser user)
            throws ServiceException;

    AllergicReactionsInfo fetchAllergicReactionsInfo(AuthUser user)
            throws ServiceException;

    boolean addAllergicFoodReaction(AllergicFoodReaction reaction, AuthUser user)
            throws ServiceException;

    boolean addAllergicMedicineReaction(AllergicMedicineReaction medicineReaction,
            AuthUser user) throws ServiceException;

    List<ExtremelyHazardousDiseaseCase> fetchCasesOfExtremelyHazardousDiseases
            (AuthUser user) throws ServiceException;

    boolean addExtremelyHazardousDisease(ExtremelyHazardousDiseaseCase disease, AuthUser user)
            throws ServiceException;
}

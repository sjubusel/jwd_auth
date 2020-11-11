package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.user_info.AllergicFoodReaction;
import by.epamtc.jwd.auth.model.user_info.AllergicReactionsInfo;
import by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

import java.util.List;

public interface ProfileDao {
    PatientInfo fetchPatientInfo(AuthUser authUser) throws DaoException;

    boolean changePatientInfo(PatientInfo changingPatientInfo, AuthUser user)
            throws DaoException;

    List<MedicalHistoryPermission> fetchMedicalHistoryPermissions(AuthUser user)
            throws DaoException;

    boolean cancelMedicalHistoryPermission(String permissionId, AuthUser user)
            throws DaoException;

    boolean addMedicalHistoryPermission(String recipientId, AuthUser user)
            throws DaoException;

    AllergicReactionsInfo fetchAllergicReactionsInfo(AuthUser user)
            throws DaoException;

    boolean addAllergicFoodReaction(AllergicFoodReaction reaction,
            AuthUser user) throws DaoException;
}

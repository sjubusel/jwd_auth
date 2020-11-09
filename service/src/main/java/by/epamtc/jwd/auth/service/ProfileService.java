package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
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
}

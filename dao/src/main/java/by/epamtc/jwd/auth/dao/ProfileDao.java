package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

public interface ProfileDao {
    PatientInfo fetchPatientInfo(AuthUser authUser) throws DaoException;

    boolean changePatientInfo(PatientInfo changingPatientInfo, AuthUser user)
            throws DaoException;
}

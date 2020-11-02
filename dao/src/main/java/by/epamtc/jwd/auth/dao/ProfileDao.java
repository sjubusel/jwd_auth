package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

public interface ProfileDao {
    PatientInfo fetchPatientInfo(AuthUser authUser);
}

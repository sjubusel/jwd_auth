package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

public interface ProfileService {
    PatientInfo fetchPatientInfo(AuthUser authUser);
}

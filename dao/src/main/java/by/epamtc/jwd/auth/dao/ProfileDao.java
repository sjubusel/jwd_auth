package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.model.user_info.PatientInfo;

public interface ProfileDao {
    PatientInfo fetchPatientInfo();
}

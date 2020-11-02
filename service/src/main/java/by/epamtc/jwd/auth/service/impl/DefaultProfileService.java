package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.service.ProfileService;

public class DefaultProfileService implements ProfileService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public PatientInfo fetchPatientInfo(AuthUser authUser) {

        return null;
    }
}

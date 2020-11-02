package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.ProfileDao;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

public class DefaultProfileDao implements ProfileDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public PatientInfo fetchPatientInfo() {

        return null;
    }
}

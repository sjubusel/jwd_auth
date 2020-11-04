package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.impl.DefaultAuthUserDao;
import by.epamtc.jwd.auth.dao.impl.DefaultHospitalReportDao;
import by.epamtc.jwd.auth.dao.impl.DefaultProfileDao;
import by.epamtc.jwd.auth.dao.impl.DefaultUploadDao;

public class DaoFactory {
    private static volatile DaoFactory instance;
    private final HospitalReportDao hospitalReportDao
            = new DefaultHospitalReportDao();
    private final AuthUserDao authUserDao = new DefaultAuthUserDao();
    private final ProfileDao profileDao = new DefaultProfileDao();
    private final UploadDao uploadDao = new DefaultUploadDao();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        DaoFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (DaoFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaoFactory();
                }
            }
        }
        return localInstance;
    }

    public HospitalReportDao getHospitalReportDao() {
        return hospitalReportDao;
    }

    public AuthUserDao getAuthUserDao() {
        return authUserDao;
    }

    public ProfileDao getProfileDao() {
        return profileDao;
    }

    public UploadDao getUploadDao() {
        return uploadDao;
    }
}

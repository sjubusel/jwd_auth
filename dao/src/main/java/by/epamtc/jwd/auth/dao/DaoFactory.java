package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.impl.DefaultAuthUserDao;
import by.epamtc.jwd.auth.dao.impl.DefaultHospitalReportDao;
import by.epamtc.jwd.auth.dao.impl.DefaultPatientDao;
import by.epamtc.jwd.auth.dao.impl.DefaultProfileDao;
import by.epamtc.jwd.auth.dao.impl.DefaultUploadDao;
import by.epamtc.jwd.auth.dao.impl.DefaultVisitDao;

/**
 * a class which provides necessary Dao interfaces on the service layer and by
 * its nature hides exact implementations of dao interfaces from a client on
 * the service layer. It is of a Singleton pattern.
 */
public final class DaoFactory {
    /**
     * a unique and only instance of this class
     */
    private static volatile DaoFactory instance;

    /**
     * a field which represent an implementation of HospitalReportDao used
     * at the present time
     */
    private final HospitalReportDao hospitalReportDao
            = new DefaultHospitalReportDao();

    /**
     * a field which represent an implementation of AuthUserDao used
     * at the present time
     */
    private final AuthUserDao authUserDao = new DefaultAuthUserDao();

    /**
     * a field which represent an implementation of ProfileDao used
     * at the present time
     */
    private final ProfileDao profileDao = new DefaultProfileDao();

    /**
     * a field which represent an implementation of UploadDao used
     * at the present time
     */
    private final UploadDao uploadDao = new DefaultUploadDao();

    /**
     * a field which represent an implementation of VisitDao used
     * at the present time
     */
    private final VisitDao visitDao = new DefaultVisitDao();

    /**
     * a field which represent an implementation of PatientDao used
     * at the present time
     */
    private final PatientDao patientDao = new DefaultPatientDao();

    /**
     * a private constructor of a Singleton pattern
     */
    private DaoFactory() {
    }

    /**
     * a method which initialize and provide thread safe instance of a
     * current type
     *
     * @return an unique and only instance of a current class
     */
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

    public VisitDao getVisitDao() {
        return visitDao;
    }

    public PatientDao getPatientDao() {
        return patientDao;
    }
}

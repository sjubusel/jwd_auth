package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.impl.DefaultAuthUserDao;
import by.epamtc.jwd.auth.dao.impl.DefaultPatientDao;
import by.epamtc.jwd.auth.dao.impl.DefaultProfileDao;
import by.epamtc.jwd.auth.dao.impl.DefaultUploadDao;
import by.epamtc.jwd.auth.dao.impl.DefaultVisitDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DaoFactoryTest {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @DisplayName("test whether Singleton is correct")
    @Test
    void testGetInstance() {
        DaoFactory expected = daoFactory;
        DaoFactory actual = DaoFactory.getInstance();
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("test whether correct implementation of AuthUserDao " +
            "interface is chosen")
    @Test
    void testGetAuthUserDao() {
        AuthUserDao authUserDao = daoFactory.getAuthUserDao();
        Assertions.assertEquals(DefaultAuthUserDao.class, authUserDao.getClass());
    }

    @DisplayName("test whether correct implementation of ProfileDao " +
            "interface is chosen")
    @Test
    void testGetProfileDao() {
        ProfileDao profileDao = daoFactory.getProfileDao();
        Assertions.assertEquals(DefaultProfileDao.class, profileDao.getClass());
    }

    @DisplayName("test whether correct implementation of UploadDao " +
            "interface is chosen")
    @Test
    void testGetUploadDao() {
        UploadDao uploadDao = daoFactory.getUploadDao();
        Assertions.assertEquals(DefaultUploadDao.class, uploadDao.getClass());
    }

    @DisplayName("test whether correct implementation of VisitDao " +
            "interface is chosen")
    @Test
    void testGetVisitDao() {
        VisitDao visitDao = daoFactory.getVisitDao();
        Assertions.assertEquals(DefaultVisitDao.class, visitDao.getClass());
    }

    @DisplayName("test whether correct implementation of PatientDao " +
            "interface is chosen")
    @Test
    void testGetPatientDao() {
        PatientDao patientDao = daoFactory.getPatientDao();
        Assertions.assertEquals(DefaultPatientDao.class, patientDao.getClass());
    }
}
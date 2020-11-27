package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.report.HospitalReport;

/**
 * an interface which is created to fetch information about current state of
 * hospital and present it for all users even for unsigned on the main page
 */
public interface HospitalReportDao {
    /**
     * a method which provide current fillability of the hospital
     *
     * @return a bean , which stores information about current fillability
     * of the hospital
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    HospitalReport receiveHospitalFillability() throws DaoException;
}

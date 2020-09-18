package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.report.HospitalReport;

public interface HospitalReportDao {
    HospitalReport receiveHospitalFillability() throws DaoException;
}

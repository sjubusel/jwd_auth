package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.report.HospitalReport;
import by.epamtc.jwd.auth.service.exception.ServiceException;

public interface HospitalReportService {
    HospitalReport receiveHospitalFillability() throws ServiceException;
}

package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.HospitalReportDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.report.HospitalDepartmentReport;
import by.epamtc.jwd.auth.model.report.HospitalReport;
import by.epamtc.jwd.auth.service.HospitalReportService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.validation.HospitalReportValidator;

import java.util.Iterator;
import java.util.List;

public class DefaultHospitalReportService implements HospitalReportService {
    private DaoFactory factory = DaoFactory.getInstance();
    private HospitalReportDao hospReportDao = factory.getHospitalReportDao();
    private HospitalReportValidator repValidator = new HospitalReportValidator();

    @Override
    public HospitalReport receiveHospitalFillability() throws ServiceException {
        try {
            return receiveValidHospitalFillability();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    private HospitalReport receiveValidHospitalFillability() throws DaoException {
        HospitalReport hReport = hospReportDao.receiveHospitalFillability();

        leaveOnlyValidHospitalReportParts(hReport);

        return hReport;
    }

    private void leaveOnlyValidHospitalReportParts(HospitalReport hReport) {
        List<HospitalDepartmentReport> deptReports = hReport.getContents();

        Iterator<HospitalDepartmentReport> iterator = deptReports.iterator();
        while (iterator.hasNext()) {
            HospitalDepartmentReport deptReport = iterator.next();
            if (!repValidator.isReportPartValid(deptReport)) {
                iterator.remove();
            }
        }
    }
}

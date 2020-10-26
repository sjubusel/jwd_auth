package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.report.HospitalReport;
import by.epamtc.jwd.auth.service.HospitalReportService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.CommandPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainCommand implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();
    private HospitalReportService hospitalReportService = factory
            .getHospitalReportService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            HospitalReport currentHospitalReport = hospitalReportService
                    .receiveHospitalFillability();
            req.setAttribute(AppAttribute.REQUEST_MAIN_PAGE_REPORT,
                    currentHospitalReport);
        } catch (ServiceException e) {
            req.setAttribute(AppAttribute.REQUEST_MAIN_PAGE_ERROR, null);
        }
        req.getRequestDispatcher(CommandPath.MAIN_JSP).forward(req, res);
    }
}

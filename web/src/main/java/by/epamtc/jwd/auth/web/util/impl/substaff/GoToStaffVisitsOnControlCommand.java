package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.VisitService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToStaffVisitsOnControlCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (GoToStaffVisitsOnControlCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        List<AdmissionDepartmentVisit> visitsOnControl = null;

        try {
            visitsOnControl = visitService.fetchControlledVisits(user);
        } catch (ServiceException e) {
            logger.error("An error while fetching of AdmissionDepartmentVisit," +
                    "which are on control of a doctor." +
                    " AuthUser: \"{}\"", user, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (visitsOnControl == null) {
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_VAL);
        } else if (visitsOnControl.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_VISITS, visitsOnControl);
        }
    }
}

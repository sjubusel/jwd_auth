package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
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

public class GoToStaffNewVisitsCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (GoToStaffNewVisitsCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        List<AdmissionDepartmentVisit> visits = null;

        try {
            visits = visitService.fetchNewVisits(user);
        } catch (ServiceException e) {
            logger.error("An error while fetching of AdmissionDepartmentVisit." +
                    " AuthUser: \"{}\"", user, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (visits == null) {
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_VAL);
        } else if (visits.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_VISITS, visits);
        }

        String acceptVisitResult = req.getParameter(AppParameter.ACCEPT_RESULT);
        if (acceptVisitResult != null) {
            req.setAttribute(AppParameter.ACCEPT_RESULT, acceptVisitResult);
        }

        String visitId = req.getParameter(AppParameter.VISIT_ID);
        if (visitId != null) {
            req.setAttribute(AppParameter.VISIT_ID, visitId);
        }

        req.getRequestDispatcher(CommandPath.SUBSTAFF_NEW_VISITS_JSP)
                .forward(req, res);
    }
}

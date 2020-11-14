package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
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

public class ChangeComplaintsOfPatientCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (ChangeComplaintsOfPatientCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String visitId = req.getParameter(AppParameter.VISIT_NUMBER);
        String complaints = req.getParameter(AppParameter.PATIENT_COMPLAINTS);

        boolean isChanged = false;

        try {
            isChanged = visitService.changeComplaints(complaints, visitId, user);
        } catch (ServiceException e) {
            logger.error("An error while changing patient's complaints.\n" +
                            "Params{user={}, visitId={}, complaints={}}", user,
                    visitId, complaints, e);
            sendRedirectWithTechError(req, res);
            return;
        }

        if (!isChanged) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res);
    }
}

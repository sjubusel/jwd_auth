package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
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

public class StaffDoctorAcceptPatient implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (StaffDoctorAcceptPatient.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String visitId = req.getParameter(AppParameter.VISIT_ID);

        boolean isAccepted;
        try {
            isAccepted = visitService.acceptPatientForTreatment(visitId, user);
        } catch (ServiceException e) {
            logger.error("An error while accepting a patient for a treatment." +
                    "Params=visitId:{}, AuthUser:{}", visitId, user, e);
            sendRedirectWithTechError(req, res);
            return;
        }

        if (!isAccepted) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res, visitId);
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_STAFF_NEW_VISITS_ACCEPT_RESULT_TECH_ERROR);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_STAFF_NEW_VISITS_ACCEPT_RESULT_VAL_ERROR);
    }

    private void sendRedirectWithSuccessMessage(HttpServletRequest req,
            HttpServletResponse res, String visitId) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_STAFF_NEW_VISITS_ACCEPT_RESULT_SUCCESS + visitId);
    }

}

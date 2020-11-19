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

public class StaffExecuteMedicinePrescriptionCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (StaffExecuteMedicinePrescriptionCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String medPrescriptionId = req.getParameter(AppParameter
                .PRESCRIPTION_ID);

        boolean isExecuted = false;
        try {
            isExecuted = visitService.executeMedPrescription(medPrescriptionId,
                    user);
        } catch (ServiceException e) {
            logger.error("An error while putting a result of a medicine " +
                    "prescription execution. Params{medPrescriptionId={}, " +
                    "user={}}", medPrescriptionId, user, e);
            sendRedirectWithTechError(req, res);
            return;
        }

        if (!isExecuted) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res);
//        res.sendRedirect(req.getContextPath() + CommandPath
//                .SUBSTAFF_GO_TO_STAFF_NEW_VISITS_ACCEPT_RESULT_TECH_ERROR);
    }
}

package by.epamtc.jwd.auth.web.util.impl.subpatient;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.visit_info.RefusalReference;
import by.epamtc.jwd.auth.service.PatientService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToPatientRefusalReferenceInDetailCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            GoToPatientRefusalReferenceInDetailCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PatientService patientService = serviceFactory.getPatientService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String referenceId = req.getParameter(AppParameter.REFUSAL_REFERENCE_ID);

        RefusalReference refusalReference = null;

        try {
            refusalReference = patientService.fetchDetailedRefusalReference(
                    referenceId, user);
        } catch (ServiceException e) {
            logger.error("An error while fetching a detailed refusal " +
                    "reference. User={}, referenceId={}", user, referenceId, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (refusalReference == null) {
            if (req.getAttribute(AppAttribute.REQUEST_ERROR) == null) {
                req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                        .REQUEST_ERROR_VALUE_VAL);
            }
        } else {
            req.setAttribute(AppAttribute.REQUEST_REFUSAL_REFERENCE,
                    refusalReference);
        }

        req.getRequestDispatcher(CommandPath
                .SUBPATIENT_REFUSAL_REFERENCE_IN_DETAIL_JSP).forward(req, res);
    }
}

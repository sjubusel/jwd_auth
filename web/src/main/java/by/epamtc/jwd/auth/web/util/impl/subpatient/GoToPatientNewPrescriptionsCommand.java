package by.epamtc.jwd.auth.web.util.impl.subpatient;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.med_info.Prescription;
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
import java.util.List;

public class GoToPatientNewPrescriptionsCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            GoToPatientNewPrescriptionsCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PatientService patientService = serviceFactory.getPatientService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        List<Prescription> prescriptions = null;

        try {
            prescriptions = patientService.fetchAllNewPrescriptions(user);
        } catch (ServiceException e) {
            logger.error("An error while fetching all new non-medicine " +
                    "prescriptions for a patient, which are not " +
                    "executed. User={}.", user, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (prescriptions == null) {
            if (req.getAttribute(AppAttribute.REQUEST_ERROR) == null) {
                req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                        .REQUEST_ERROR_VALUE_VAL);
            }
        } else if (prescriptions.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_PRESCRIPTIONS,
                    prescriptions);
        }

        String result = req.getParameter(AppParameter.DISAGREE_RESULT);
        if (result != null) {
            req.setAttribute(AppParameter.DISAGREE_RESULT, result);
        }

        req.getRequestDispatcher(CommandPath.SUBPATIENT_NEW_PRESCR_JSP)
                .forward(req, res);
    }
}

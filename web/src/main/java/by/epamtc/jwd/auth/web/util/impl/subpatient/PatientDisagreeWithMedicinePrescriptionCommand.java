package by.epamtc.jwd.auth.web.util.impl.subpatient;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
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

public class PatientDisagreeWithMedicinePrescriptionCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            PatientDisagreeWithMedicinePrescriptionCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PatientService patientService = serviceFactory.getPatientService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String prescriptionId = req.getParameter(AppParameter.PRESCRIPTION_ID);
        String disagreementDescription = req.getParameter(AppParameter
                .DISAGREEMENT_CONTENTS);
        boolean isDisagree = false;

        try {
            isDisagree = patientService.disagreeWithMedicinePrescription(user,
                    prescriptionId, disagreementDescription);
        } catch (ServiceException e) {
            logger.error("An error while a patient disagrees with a medicine " +
                    "prescription.", e);
            sendRedirectWithTechError(req, res);
        }

        if (!isDisagree) {
            sendRedirectWithValidationError(req, res);
        }

        sendRedirectWithSuccessMessage(req, res);
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPATIENT_GO_TO_NEW_MED_PRESCR_DISAGREE_RESULT_TECH_ERROR);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPATIENT_GO_TO_NEW_MED_PRESCR_DISAGREE_RESULT_VAL_ERROR);
    }

    private void sendRedirectWithSuccessMessage(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPATIENT_GO_TO_NEW_MED_PRESCR_DISAGREE_RESULT_SUCCESS);
    }
}

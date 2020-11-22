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

public class RefusalMedicineRecommendationAddCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            RefusalMedicineRecommendationAddCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String visitId = req.getParameter(AppParameter.VISIT_ID);
        String medicineId = req.getParameter(AppParameter.HIDDEN_MEDICINE_ID);
        String intakeInstruction = req.getParameter(AppParameter
                .INTAKE_INSTRUCTIONS);

        boolean isAdded = false;

        try {
            isAdded = visitService.addRefusalMedicineRecommendation(medicineId,
                    intakeInstruction, visitId, user);
        } catch (ServiceException e) {
            logger.error("An error while adding a refusal medicine " +
                            "recommendation. Params{user={}, visitId={}, " +
                            "medicineId={}, intakeInstruction={}}", user, visitId,
                    medicineId, intakeInstruction, e);
            sendRedirectWithTechError(req, res, visitId);
        }

        if (!isAdded) {
            sendRedirectWithValidationError(req, res, visitId);
        }

        sendRedirectWithSuccess(req, res, visitId);
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res, String visitId) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_REFUSE_TO_HOSPITALIZE_ADD_RESULT_TECH_ERROR
                + visitId);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res, String visitId) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_REFUSE_TO_HOSPITALIZE_ADD_RESULT_VAL_ERROR
                + visitId);
    }

    private void sendRedirectWithSuccess(HttpServletRequest req,
            HttpServletResponse res, String visitId) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_REFUSE_TO_HOSPITALIZE_ADD_RESULT_SUCCESS
                + visitId);
    }
}

package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.VisitService;
import by.epamtc.jwd.auth.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormRefusalReferenceCancelCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            FormRefusalReferenceCancelCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String visitId = req.getParameter(AppParameter.VISIT_ID);
        String refusalRecommendations = req.getParameter(AppParameter
                .REFUSAL_RECOMMENDATIONS);

        int referenceId;

        try {
            referenceId = visitService.formRefusalReference(refusalRecommendations,
                    visitId, user);
        } catch (Exception e) {
            logger.error("An error while forming a refusal references. Params" +
                            "{user={}, visitId={}, refusalRecommendations={}}",
                    user, visitId, refusalRecommendations, e);
            sendRedirectWithTechError(req, res, visitId);
            return;
        }

        if (referenceId <= 0) {
            sendRedirectWithValidationError(req, res, visitId);
            return;
        }

        sendRedirectWithSuccess(req, res, referenceId);
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res, String visitId) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_REFUSE_TO_HOSPITALIZE_FORM_RESULT_TECH_ERROR
                + visitId);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res, String visitId) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_REFUSE_TO_HOSPITALIZE_FORM_RESULT_VAL_ERROR
                + visitId);
    }

    private void sendRedirectWithSuccess(HttpServletRequest req,
            HttpServletResponse res, int referenceId) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_REFUSAL_REFERENCE_IN_DETAIL_SUCCESS_RESULT
                + referenceId);
    }
}

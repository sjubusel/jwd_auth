package by.epamtc.jwd.auth.web.util.impl.subpatient;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppConstant;
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
import java.util.List;

public class GoToRefusalReferencesForPatientCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            GoToRefusalReferencesForPatientCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PatientService patientService = serviceFactory.getPatientService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);

        String pageNumber = req.getParameter(AppParameter.PAGE_NUMBER);
        if (pageNumber == null) {
            pageNumber = AppConstant.DEFAULT_PAGE_NUMBER;
        }
        req.setAttribute(AppAttribute.PAGE, pageNumber);

        List<RefusalReference> references = null;

        try {
            references = patientService.fetchRefusalReferences(pageNumber, user);
        } catch (ServiceException e) {
            logger.error("An error while fetching refusal references for " +
                    "a patient. User={}", user, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (references == null) {
            if (req.getAttribute(AppAttribute.REQUEST_ERROR) == null) {
                req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                        .REQUEST_ERROR_VALUE_VAL);
            }
        } else if (references.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_REFUSAL_REFERENCES,
                    references);
        }

        req.getRequestDispatcher(CommandPath.SUBPATIENT_REFUSAL_REFERENCES_JSP)
                .forward(req, res);
    }
}

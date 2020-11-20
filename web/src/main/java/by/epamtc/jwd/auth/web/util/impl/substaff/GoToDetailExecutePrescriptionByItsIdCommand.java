package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.med_info.Prescription;
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

public class GoToDetailExecutePrescriptionByItsIdCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            GoToDetailExecutePrescriptionByItsIdCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String prescriptionId = req.getParameter(AppParameter
                .PRESCRIPTION_ID_INPUT);
        Prescription prescription = null;

        try {
            prescription = visitService.fetchVisitPrescriptionById(prescriptionId,
                    user);
        } catch (ServiceException e) {
            logger.error("An error while fetching detailed information " +
                    "about a prescription by its id. Params={user={}, " +
                    "prescriptionId={}}", user, prescriptionId, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (prescription == null) {
            if (req.getAttribute(AppAttribute.REQUEST_ERROR) == null) {
                req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                        .REQUEST_ERROR_VALUE_VAL);
            }
        } else {
            req.setAttribute(AppAttribute.REQUEST_PRESCRIPTION, prescription);
        }

        req.getRequestDispatcher(CommandPath.EXECUTE_PRESCRIPTION_BY_ID_JSP)
                .forward(req, res);
    }
}

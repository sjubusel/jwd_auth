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

public class GoToRefuseToHospitalizeCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            GoToRefuseToHospitalizeCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String visitId = req.getParameter(AppParameter.VISIT_ID);
        List<RefusalMedicineRecommendation> medRecoms = null;

        try {
            medRecoms = visitService.fetchRefusalMedicineRecommendations(visitId,
                    user);
        } catch (ServiceException e) {
            logger.error("An error while fetching a refusal medicine " +
                    "recommendations", e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (medRecoms == null) {
            if (req.getAttribute(AppAttribute.REQUEST_ERROR) == null) {
                req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                        .REQUEST_ERROR_VALUE_VAL);
            }
        } else if (medRecoms.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_REFUSAL_MED_RECOMS, medRecoms);
        }

        req.getRequestDispatcher(CommandPath.SUBSTAFF_REFUSE_TO_HOSPITALIZE_JSP)
                .forward(req, res);
    }
}

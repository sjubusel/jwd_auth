package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase;
import by.epamtc.jwd.auth.service.ProfileService;
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

public class GoToProfileExtremelyHazardousDiseasesCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (GoToProfileExtremelyHazardousDiseasesCommand.class);
    private ServiceFactory factory = ServiceFactory.getInstance();
    private ProfileService profileService = factory.getProfileService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession()
                .getAttribute(AppAttribute.SESSION_AUTH_USER);
        List<ExtremelyHazardousDiseaseCase> diseaseList = null;

        try {
            diseaseList = profileService.fetchCasesOfExtremelyHazardousDiseases(user);
        } catch (ServiceException e) {
            logger.error("An error while fetching of ExtremelyHazardousDisease " +
                    "information. AuthUser: \"{}\"", user, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (diseaseList == null) {
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_VAL);
        } else if (diseaseList.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_EXTEREMELY_HAZARDOUS_DISEASES,
                    diseaseList);
        }

        String addResult = req.getParameter(AppParameter.ADD_RESULT);
        if (addResult != null) {
            req.setAttribute(AppParameter.ADD_RESULT, addResult);
        }

        req.getRequestDispatcher(CommandPath
                .SUBPROFILE_EXTREMELY_HAZARDOUS_DISEASES_JSP).forward(req, res);
    }
}

package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.user_info.MedicalHistoryPermission;
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

public class GoToProfileMedicalHistoryPermissionCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            GoToProfileMedicalHistoryPermissionCommand.class);

    private ServiceFactory factory = ServiceFactory.getInstance();
    private ProfileService profileService = factory.getProfileService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession()
                .getAttribute(AppAttribute.SESSION_AUTH_USER);
        List<MedicalHistoryPermission> permissions = null;
        try {
            permissions = profileService.fetchMedicalHistoryPermissions(user);
        } catch (ServiceException e) {
            logger.error("An error while fetching of permissions for\n" +
                    "medical history. AuthUser: \"{}\"", user, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (permissions == null) {
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_VAL);
        } else if (permissions.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_MEDICAL_HISTORY_PERMISSIONS,
                    permissions);
        }

        String permissionDeleteResult = req.getParameter(AppParameter.DELETE_RESULT);
        if (permissionDeleteResult != null) {
            req.setAttribute(AppParameter.DELETE_RESULT, permissionDeleteResult);
        }

        req.getRequestDispatcher(CommandPath.SUBPROFILE_MEDICAL_HISTORY_PERMISSION_jsp)
                .forward(req, res);
    }
}

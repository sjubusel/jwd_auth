package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.service.ProfileService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileMedicalHistoryPermissionDeleteCommand implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProfileService profileService = serviceFactory.getProfileService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);

        String permission = req.getParameter("permissionIdInput");
        boolean isDeleted = false;

        try {
            isDeleted = profileService.deleteMedicalHistoryPermission(permission, user);
        } catch (ServiceException e) {
            // TODO log4j
            sendRedirectWithTechError(req, res);
            return;
        }

        if (!isDeleted) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res);
    }
}

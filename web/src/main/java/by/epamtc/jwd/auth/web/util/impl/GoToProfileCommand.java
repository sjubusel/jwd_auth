package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.service.ProfileService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.model.constant.CommandPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToProfileCommand implements Command {
    private ServiceFactory factory = ServiceFactory.getInstance();
    private ProfileService profileService = factory.getProfileService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // TODO create: if error, then forward on jsp
        AuthUser currentUser = (AuthUser) req.getSession()
                .getAttribute(AppAttribute.SESSION_AUTH_USER);
        PatientInfo patientInfo;

        try {
            patientInfo = profileService.fetchPatientInfo(currentUser);
        } catch (ServiceException e) {
            // TODO log4j in catch of ServiceException
            res.sendRedirect(req.getContextPath() + CommandPath
                    .PROFILE_TECH_ERROR);
            return;
        }

        if (patientInfo == null) {
            res.sendRedirect(req.getContextPath() + CommandPath
                    .PROFILE_AUTH_USER_VALIDATION_ERROR);
            return;
        }

        req.setAttribute(AppAttribute.REQUEST_PATIENT_INFO, patientInfo);
        req.getRequestDispatcher(CommandPath.PROFILE_JSP).forward(req, res);
    }
}

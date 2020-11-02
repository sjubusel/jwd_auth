package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.service.ProfileService;
import by.epamtc.jwd.auth.service.ServiceFactory;
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
        AuthUser currentUser = (AuthUser) req.getSession()
                .getAttribute(AppAttribute.SESSION_AUTH_USER);
        PatientInfo patientInfo = profileService.fetchPatientInfo(currentUser);

        req.setAttribute(AppAttribute.REQUEST_PATIENT_INFO, patientInfo);
        req.getRequestDispatcher(CommandPath.PROFILE_JSP).forward(req, res);
    }
}

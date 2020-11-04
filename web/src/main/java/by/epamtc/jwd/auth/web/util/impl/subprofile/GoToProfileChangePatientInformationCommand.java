package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.service.ProfileService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToProfileChangePatientInformationCommand implements Command {
    private ServiceFactory factory = ServiceFactory.getInstance();
    private ProfileService profileService = factory.getProfileService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser currentUser = (AuthUser) req.getSession()
                .getAttribute(AppAttribute.SESSION_AUTH_USER);
        PatientInfo patientInfo = null;

        try {
            patientInfo = profileService.fetchPatientInfo(currentUser);
        } catch (ServiceException e) {
            // TODO log4j in catch of ServiceException
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (patientInfo == null) {
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_VAL);
        }

        req.setAttribute(AppAttribute.REQUEST_PATIENT_INFO, patientInfo);
        req.getRequestDispatcher(CommandPath.SUBPROFILE_CHANGE_PATIENT_INFO_JSP)
                .forward(req, res);
    }
}

package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
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

public class GoToProfileChangePatientInformationCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            GoToProfileChangePatientInformationCommand.class);

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
            logger.error("An error occurred while preparing of a web page,\n" +
                    "which is used to change patient (user) information.\n" +
                    "AuthUser: \"{}\"", currentUser, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (patientInfo == null) {
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_VAL);
        }

        String photoUploadResult = req.getParameter(AppParameter.PHOTO_UPLOAD_MESSAGE);
        if (photoUploadResult != null) {
            req.setAttribute(AppAttribute.REQUEST_PHOTO_UPLOAD, photoUploadResult);
        }

        String patientInfoChangeResult = req.getParameter(AppParameter.CHANGE_RESULT);
        if (patientInfoChangeResult != null) {
            req.setAttribute(AppParameter.CHANGE_RESULT, patientInfoChangeResult);
        }

        req.setAttribute(AppAttribute.REQUEST_PATIENT_INFO, patientInfo);
        req.getRequestDispatcher(CommandPath.SUBPROFILE_CHANGE_PATIENT_INFO_JSP)
                .forward(req, res);
    }
}

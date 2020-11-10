package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.service.ProfileService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.ChangingInfoCompiler;
import by.epamtc.jwd.auth.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileChangePatientInfoCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            ProfileChangePatientInfoCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProfileService profileService = serviceFactory.getProfileService();
    private ChangingInfoCompiler changingInfoCompiler = ChangingInfoCompiler
            .getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        PatientInfo changingPatientInfo = changingInfoCompiler
                .compileChangingPatientInfo(req);

        boolean isChanged;
        try {
            isChanged = profileService.changePatientInfo(changingPatientInfo, user);
        } catch (ServiceException | RuntimeException e) {
            logger.error("An error while changing patient info with " +
                    "the following parameters\n" +
                    "AuthUser: \"{}\"\n" +
                    "PatientInfo: \"{}\".", user, changingPatientInfo, e);
            sendRedirectWithTechError(req, res);
            return;
        }

        if (!isChanged) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res);
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_CHANGE_RESULT_TECH_ERROR);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_CHANGE_RESULT_VALID_ERROR);
    }

    private void sendRedirectWithSuccessMessage(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_CHANGE_RESULT_SUCCESS);
    }
}

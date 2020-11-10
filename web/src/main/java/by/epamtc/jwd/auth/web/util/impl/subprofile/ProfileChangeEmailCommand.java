package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.ChangeResult;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileChangeEmailCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            ProfileChangeEmailCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AuthUserService authUserService = serviceFactory.getAuthUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String password = req.getParameter(AppParameter.PASSWORD);
        String email = req.getParameter(AppParameter.EMAIL);

        String resultEmail = null;
        try {
            resultEmail = authUserService.changeEmail(email, password, user);
        } catch (ServiceException e) {
            logger.error("An error while changing of email with the following\n" +
                    "parameters \"password: {}\", \"email: {}\",\n" +
                    "AuthUser: \"{}\"", password, email, user, e);
            sendRedirectWithTechError(req, res);
        }

        if (resultEmail == null) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        if (resultEmail.equals(ChangeResult.DUPLICATE_ERROR.name())) {
            sendRedirectWithDuplicateError(req, res);
            return;
        }

        if (resultEmail.equals(ChangeResult.ILLEGAL_PASSWORD.name())) {
            sendRedirectWithIllegalPasswordError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res);
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_EMAIL_CHANGE_RESULT_TECH_ERROR);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_EMAIL_CHANGE_RESULT_VALID_ERROR);
    }

    private void sendRedirectWithDuplicateError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_EMAIL_CHANGE_RESULT_DUPLICATE_ERROR);
    }

    private void sendRedirectWithIllegalPasswordError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_EMAIL_CHANGE_RESULT_ILLEGAL_PASSWORD_ERROR);
    }

    private void sendRedirectWithSuccessMessage(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_EMAIL_CHANGE_SUCCESSFUL_RESULT);
    }
}

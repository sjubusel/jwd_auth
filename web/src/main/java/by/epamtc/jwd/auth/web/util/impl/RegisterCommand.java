package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.web.util.RegistrationInfoCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(RegisterCommand
            .class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AuthUserService authUserService = serviceFactory.getAuthUserService();
    private RegistrationInfoCompiler regInfCompiler = RegistrationInfoCompiler
            .getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        RegistrationInfo regInfo = regInfCompiler.compileRegInfo(req);
        AuthUser user;

        try {
            user = authUserService.register(regInfo);
        } catch (ServiceException e) {
            logger.error("An error occurred while registration of a user\n" +
                    "with the following params:\nregInfo: \"{}\"", regInfo, e);
            sendRedirectWithTechError(req, res);
            return;
        }

        if (user == null) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        if (doesUserWithTheseCredentialsExist(user)) {
            sendRedirectWithDuplicateError(user, req, res);
            return;
        }

        req.getSession().setAttribute(AppAttribute.SESSION_AUTH_USER, user);
        res.sendRedirect(req.getContextPath() + CommandPath.PROFILE_GET);
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .REGISTER_TECH_ERROR);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .REG_INFO_VAL_ERROR);
    }

    private void sendRedirectWithDuplicateError(AuthUser user,
            HttpServletRequest req, HttpServletResponse res) throws IOException {
        if (doesUserWithBothTheseLoginAndEmailExist(user)) {
            res.sendRedirect(req.getContextPath() + CommandPath
                    .REGISTER_DUPLICATE_ERROR);
        } else if (doesUserWithOnlyThisLoginExist(user)) {
            res.sendRedirect(req.getContextPath() + CommandPath
                    .REGISTER_DUPLICATE_ERROR_BY_LOGIN);
        } else {
            res.sendRedirect(req.getContextPath() + CommandPath
                    .REGISTER_DUPLICATE_ERROR_BY_EMAIL);
        }
    }

    private boolean doesUserWithTheseCredentialsExist(AuthUser user) {
        return user.getId() == AppConstant.DUPLICATE_AUTH_USER_LOGIN_ID
                || user.getUserId() == AppConstant.DUPLICATE_AUTH_USER_EMAIL_ID;
    }

    private boolean doesUserWithBothTheseLoginAndEmailExist(AuthUser user) {
        return user.getId() == AppConstant.DUPLICATE_AUTH_USER_LOGIN_ID
                && user.getUserId() == AppConstant.DUPLICATE_AUTH_USER_EMAIL_ID;
    }

    private boolean doesUserWithOnlyThisLoginExist(AuthUser user) {
        return user.getId() == AppConstant.DUPLICATE_AUTH_USER_LOGIN_ID;
    }
}

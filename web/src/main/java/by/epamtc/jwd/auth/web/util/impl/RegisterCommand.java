package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.AuthDataValidationServiceException;
import by.epamtc.jwd.auth.service.exception.LoginValidationServiceException;
import by.epamtc.jwd.auth.service.exception.PasswordValidationServiceException;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.web.util.RegistrationInfoCompiler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AuthUserService authUserService = serviceFactory.getAuthUserService();
    private RegistrationInfoCompiler regInfCompiler = RegistrationInfoCompiler
            .getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        RegistrationInfo regInfo = regInfCompiler.compileRegInfo(req);
        AuthUser user;

        // TODO Change register parameters
        try {
            user = authUserService.register(login, password);
        } catch (AuthDataValidationServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPath.REGISTER_AUTH_ERROR);
            return;
        } catch (LoginValidationServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPath.REGISTER_LOGIN_ERROR);
            return;
        } catch (PasswordValidationServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPath.REGISTER_PASSWORD_ERROR);
            return;
        } catch (ServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPath.REGISTER_TECH_ERROR);
            return;
        }

        if (user != null) {
            req.getSession().setAttribute(AppAttribute.SESSION_AUTH_DATA, user);
            res.sendRedirect(req.getContextPath() + CommandPath.PROFILE_GET);
        } else {
            res.sendRedirect(req.getContextPath()
                    + CommandPath.REGISTER_DUPLICATE_ERROR);
        }
    }
}


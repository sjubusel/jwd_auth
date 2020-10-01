package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_user.AuthUser;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.AuthDataValidationServiceException;
import by.epamtc.jwd.auth.service.exception.LoginValidationServiceException;
import by.epamtc.jwd.auth.service.exception.PasswordValidationServiceException;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.web.util.constant.CommandPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AuthUserService authUserService = serviceFactory.getAuthUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthUser user;

        try {
            user = authUserService.register(login, password);
        } catch (AuthDataValidationServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.REGISTER_AUTH_ERROR);
            return;
        } catch (LoginValidationServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.REGISTER_LOGIN_ERROR);
            return;
        } catch (PasswordValidationServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.REGISTER_PASSWORD_ERROR);
            return;
        } catch (ServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.REGISTER_TECH_ERROR);
            return;
        }

        if (user != null) {
            req.getSession().setAttribute("authUser", user);
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.PROFILE_GET);
        } else {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.REGISTER_DUPLICATE_ERROR);
        }
    }
}


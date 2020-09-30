package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_user.AuthUser;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.AuthDataValidationServiceException;
import by.epamtc.jwd.auth.service.exception.LoginValidationServiceException;
import by.epamtc.jwd.auth.service.exception.PasswordValidationServiceException;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;

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
            res.sendRedirect(req.getContextPath() + "/main?command=go-to-register&error=auth-data");
            return;
        } catch (LoginValidationServiceException e) {
            res.sendRedirect(req.getContextPath() + "/main?command=go-to-register&error=login");
            return;
        } catch (PasswordValidationServiceException e) {
            res.sendRedirect(req.getContextPath() + "/main?command=go-to-register&error=pass");
            return;
        } catch (ServiceException e) {
            res.sendRedirect(req.getContextPath() + "/main?command=go-to-register&error=tech");
            return;
        }

        if (user != null) {
            req.getSession().setAttribute("authUser", user);
            res.sendRedirect(req.getContextPath() + "/personal");
        } else {
            res.sendRedirect(req.getContextPath() + "/main?command=go-to-register&error=duplicate");
        }
    }
}


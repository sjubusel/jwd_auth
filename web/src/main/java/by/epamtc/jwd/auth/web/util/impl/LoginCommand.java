package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_user.AuthUser;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.web.util.constant.AppAttributes;
import by.epamtc.jwd.auth.web.util.constant.CommandPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginCommand implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AuthUserService authUserService = serviceFactory.getAuthUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

        AuthUser user;
        try {
            user = authUserService.login(login, passwordBytes);
        } catch (ServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.LOGIN_TECH_ERROR);
            return;
        }

        if (user == null) {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.LOGIN_SIMPLE_ERROR);
            return;
        }

        req.getSession().setAttribute(AppAttributes.SESSION_AUTH_DATA, user);
        res.sendRedirect(req.getContextPath() + CommandPaths.PROFILE_GET);
    }
}

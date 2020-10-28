package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.AuthenticationInfo;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AuthUserService authUserService = serviceFactory.getAuthUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String login = req.getParameter(AppParameter.LOGIN);
        String password = req.getParameter(AppParameter.PASSWORD);
        AuthenticationInfo authInfo = new AuthenticationInfo(login, password);

        AuthUser user;
        try {
            user = authUserService.login(authInfo);
        } catch (ServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPath.LOGIN_TECH_ERROR);
            return;
        }

        if (user == null) {
            res.sendRedirect(req.getContextPath() + CommandPath
                    .LOGIN_SIMPLE_ERROR);
            return;
        }

        req.getSession().setAttribute(AppAttribute.SESSION_AUTH_USER, user);
        res.sendRedirect(req.getContextPath() + CommandPath.PROFILE_GET);
    }
}

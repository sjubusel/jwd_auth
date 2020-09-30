package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_user.AuthUser;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AuthUserService authUserService = serviceFactory.getAuthUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthUser user = null;

        try {
            user = authUserService.register(login, password);
        } catch (ServiceException e) {
            req.setAttribute("error", "По техническим причинам " +
                    "зарегистрироваться в системе не представляется возможным");
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
                    .forward(req, res);
        }

        if (user != null) {
            req.getSession().setAttribute("authUser", user);
            res.sendRedirect(req.getContextPath() + "/personal");
        } else {
            req.setAttribute("error", "Введённый логин уже занят");
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, res);
        }
    }
}


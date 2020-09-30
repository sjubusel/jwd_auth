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

public class LoginCommand implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AuthUserService authUserService = serviceFactory.getAuthUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        AuthUser user = null;
        try {
            user = authUserService.login(login, password);
        } catch (ServiceException e) {
            req.setAttribute("error", "по техническим причинам войти" +
                    " в систему не представляется возможным");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
        }

        if (user == null) {
            req.setAttribute("error", "логин или пароль недействительны");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
            return;
        }

        req.getSession().setAttribute("authUser", user);
        res.sendRedirect("/personal");
    }
}

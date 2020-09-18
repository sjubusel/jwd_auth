package by.epamtc.jwd.auth.web.servlet;

import by.epamtc.jwd.auth.model.auth_user.AuthUser;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 5244741177449100707L;

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    AuthUserService authUserService = serviceFactory.getAuthUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object authUser = req.getSession().getAttribute("authUser");
        if (authUser == null) {
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/personal");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthUser user = null;

        try {
            user = authUserService.register(login, password);
        } catch (ServiceException e) {
            req.setAttribute("error", "По техническим причинам " +
                    "зарегистрироваться в системе не представляется возможным");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }

        if (user != null) {
            req.getSession().setAttribute("authUser", user);
            resp.sendRedirect(getServletContext().getContextPath() + "/personal");
        } else {
            req.setAttribute("error", "Введённый логин уже занят");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}

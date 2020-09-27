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

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -6562296086627587327L;

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    AuthUserService authUserService = serviceFactory.getAuthUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Object authUser = req.getSession().getAttribute("authUser");
        if (authUser == null) {
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
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
            user = authUserService.login(login, password);
        } catch (ServiceException e) {
            req.setAttribute("error", "по техническим причинам войти" +
                    " в систему не представляется возможным");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }

        if (user == null) {
            req.setAttribute("error", "логин или пароль недействительны");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("authUser", user);
        resp.sendRedirect(getServletContext().getContextPath() + "/personal");
    }
}

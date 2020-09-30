package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToRegisterCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String error = req.getParameter("error");
        if (error != null) {
            req.setAttribute("error", error);
        }

        Object authUser = req.getSession().getAttribute("authUser");
        if (authUser == null) {
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
                    .forward(req, res);
            return;
        }
        res.sendRedirect(req.getContextPath() + "/profile?command=go-to-profile");
    }
}

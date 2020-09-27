package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getSession().removeAttribute("authUser");
        req.getSession().invalidate();
        req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, res);
    }
}

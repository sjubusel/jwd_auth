package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToRegisterCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String error = req.getParameter(AppParameter.ERROR);
        if (error != null) {
            req.setAttribute(AppAttribute.REQUEST_ERROR, error);
        }

        Object authUser = req.getSession()
                .getAttribute(AppAttribute.SESSION_AUTH_USER);
        if (authUser == null) {
            req.getRequestDispatcher(CommandPath.REGISTER_JSP)
                    .forward(req, res);
            return;
        }
        res.sendRedirect(req.getContextPath() + CommandPath.PROFILE_GET);
    }
}

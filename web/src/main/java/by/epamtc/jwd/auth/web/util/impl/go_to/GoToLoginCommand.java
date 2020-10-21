package by.epamtc.jwd.auth.web.util.impl.go_to;

import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.model.constant.AppAttributes;
import by.epamtc.jwd.auth.model.constant.AppParameters;
import by.epamtc.jwd.auth.model.constant.CommandPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToLoginCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String error = req.getParameter(AppParameters.ERROR);
        if (error != null) {
            req.setAttribute(AppAttributes.REQUEST_LOGIN_REGISTER_ERROR, error);
        }

        Object authUser = req.getSession()
                .getAttribute(AppAttributes.SESSION_AUTH_DATA);
        if (authUser == null) {
            req.getRequestDispatcher(CommandPaths.LOGIN_JSP).forward(req, res);
            return;
        }
        res.sendRedirect(req.getContextPath() + CommandPaths.PROFILE_GET);
    }
}

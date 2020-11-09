package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToProfileChangeEmailCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String emailChangeResult = req.getParameter(AppParameter.CHANGE_RESULT);
        if (emailChangeResult != null) {
            req.setAttribute(AppParameter.CHANGE_RESULT, emailChangeResult);
        }

        req.getRequestDispatcher(CommandPath.SUBPROFILE_CHANGE_EMAIL_JSP)
                .forward(req, res);
    }
}

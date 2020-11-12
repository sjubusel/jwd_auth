package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToStaffRegisterVisitCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String registerResult = req.getParameter(AppParameter.ADD_RESULT);
        if (registerResult != null) {
            req.setAttribute(AppParameter.ADD_RESULT, registerResult);
        }
        req.getRequestDispatcher(CommandPath.SUBSTAFF_REGISTER_VISIT_JSP)
                .forward(req, res);
    }
}

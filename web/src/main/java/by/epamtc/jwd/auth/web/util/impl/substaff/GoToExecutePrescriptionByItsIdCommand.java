package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToExecutePrescriptionByItsIdCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String result = req.getParameter(AppParameter.EXECUTION_RESULT);
        if (result != null) {
            req.setAttribute(AppParameter.EXECUTION_RESULT, result);
        }

        req.getRequestDispatcher(CommandPath.EXECUTE_PRESCRIPTION_BY_ID_JSP)
                .forward(req, res);
    }
}

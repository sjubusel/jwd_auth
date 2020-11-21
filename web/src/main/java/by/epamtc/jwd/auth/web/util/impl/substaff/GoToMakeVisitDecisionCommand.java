package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToMakeVisitDecisionCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String visitId = req.getParameter(AppParameter.VISIT_ID);
        req.setAttribute(AppParameter.VISIT_ID, visitId);

        String result = req.getParameter(AppParameter.REFUSAL_RESULT);
        if (result != null) {
            req.setAttribute(AppParameter.REFUSAL_RESULT, result);
        }

        req.getRequestDispatcher(CommandPath.SUBSTAFF_MAKE_VISIT_DECISION_JSP)
                .forward(req, res);
    }
}

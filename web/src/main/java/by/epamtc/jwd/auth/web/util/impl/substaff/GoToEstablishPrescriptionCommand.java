package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToEstablishPrescriptionCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String visitId = req.getParameter(AppParameter.VISIT_ID);
        req.setAttribute(AppParameter.VISIT_ID, visitId);

        String addResult = req.getParameter(AppParameter.ADD_RESULT);
        if (addResult != null) {
            req.setAttribute(AppParameter.ADD_RESULT, addResult);
        }

        req.getRequestDispatcher(CommandPath.SUBSTAFF_ESTABLISH_PRESCR_JSP)
                .forward(req, res);
    }
}

package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToEstablishDiagnosisCommand implements by.epamtc.jwd.auth.web.util.Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String visitId = req.getParameter(AppParameter.VISIT_ID);
        req.setAttribute(AppParameter.VISIT_ID, visitId);

        String changeResult = req.getParameter(AppParameter.CHANGE_RESULT);
        if (changeResult != null) {
            req.setAttribute(AppParameter.CHANGE_RESULT, changeResult);
        }

        req.getRequestDispatcher(CommandPath.SUBSTAFF_ESTABLISH_DIAGNOSIS_JSP)
                .forward(req, res);
    }
}

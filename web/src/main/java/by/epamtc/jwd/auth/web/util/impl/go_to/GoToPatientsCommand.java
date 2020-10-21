package by.epamtc.jwd.auth.web.util.impl.go_to;

import by.epamtc.jwd.auth.model.constant.CommandPaths;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToPatientsCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher(CommandPaths.PATIENTS_JSP).forward(req, res);
    }
}

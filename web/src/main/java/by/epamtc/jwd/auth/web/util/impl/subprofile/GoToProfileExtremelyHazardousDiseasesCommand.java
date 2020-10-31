package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToProfileExtremelyHazardousDiseasesCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/subprofile/extremelyHazardousDiseases.jsp").forward(req, res);
    }
}

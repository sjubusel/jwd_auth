package by.epamtc.jwd.auth.web.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException;
}

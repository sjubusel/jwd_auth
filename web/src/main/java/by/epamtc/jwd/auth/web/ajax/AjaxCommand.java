package by.epamtc.jwd.auth.web.ajax;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AjaxCommand {
    void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException;
}

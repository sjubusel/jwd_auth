package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute("reportError", "Ресурс недоступен." +
                "Просьба обратиться позднее.");
        req.getRequestDispatcher("/main.jsp").forward(req, res);
    }
}

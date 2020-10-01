package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.model.constant.AppAttributes;
import by.epamtc.jwd.auth.model.constant.CommandPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute(AppAttributes.REQUEST_MAIN_PAGE_ERROR,
                "Ресурс недоступен. Просьба обратиться позднее.");
        req.getRequestDispatcher(CommandPaths.MAIN_JSP).forward(req, res);
    }
}

package by.epamtc.jwd.auth.web.servlet;

import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.web.util.ajax.AjaxCommandProvider;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 6469747034052425842L;

    private AjaxCommandProvider ajaxProvider = AjaxCommandProvider.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter(AppParameter.COMMAND);
        ajaxProvider.execute(command.toUpperCase(), req, resp);
    }
}

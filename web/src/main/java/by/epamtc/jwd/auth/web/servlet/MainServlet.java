package by.epamtc.jwd.auth.web.servlet;

import by.epamtc.jwd.auth.web.util.CommandProvider;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 14924641025210861L;

    private CommandProvider commandProvider = CommandProvider.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String command = req.getParameter(AppParameter.COMMAND);
        if (command == null) {
            command = CommandName.MAIN;
        }
        commandProvider.execute(command.toUpperCase(), req, resp);
    }
}

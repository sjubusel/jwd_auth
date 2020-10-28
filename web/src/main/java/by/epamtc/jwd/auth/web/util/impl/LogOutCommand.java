package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.model.constant.AppAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        req.getSession().removeAttribute(AppAttribute.SESSION_AUTH_USER);
        req.getSession().invalidate();
        res.sendRedirect(req.getContextPath());
    }
}

package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.web.util.LanguageProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    private LanguageProvider languageProvider = LanguageProvider.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        HttpSession session = req.getSession();
        String language = (String) session.getAttribute("language");
        String languageCode = languageProvider.provideLanguageCode(language);

        session.setAttribute("language", languageCode);
        res.sendRedirect(req.getContextPath() + "/main");
    }
}

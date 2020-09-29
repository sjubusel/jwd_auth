package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.web.util.LanguageProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    private LanguageProvider languageProvider = LanguageProvider.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String language = req.getParameter("language");
        String languageCode = languageProvider.provideLanguageCode(language);

        req.getSession().setAttribute("language", languageCode);

        String previousUrl = req.getHeader("Referer");
        res.sendRedirect(previousUrl);
    }
}

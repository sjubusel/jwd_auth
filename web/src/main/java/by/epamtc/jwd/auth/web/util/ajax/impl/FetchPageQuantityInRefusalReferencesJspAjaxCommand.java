package by.epamtc.jwd.auth.web.util.ajax.impl;

import by.epamtc.jwd.auth.model.ajax.AjaxParameter;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.service.ajax.AjaxFetchService;
import by.epamtc.jwd.auth.service.ajax.AjaxServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.ajax.AjaxCommand;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FetchPageQuantityInRefusalReferencesJspAjaxCommand implements AjaxCommand {
    private static final Logger logger = LoggerFactory.getLogger(
            FetchPageQuantityInRefusalReferencesJspAjaxCommand.class);
    private AjaxServiceFactory ajaxServiceFactory = AjaxServiceFactory.getInstance();
    private AjaxFetchService ajaxFetchService = ajaxServiceFactory
            .getAjaxFetchService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = ((AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER));
        int pagesAmount = 0;

        try {
            pagesAmount = ajaxFetchService.fetchAmountOfPagesOfReferences(user);
        } catch (ServiceException e) {
            logger.error("An error while fetching an amount of pages of " +
                    "refusal references. User={}", user, e);
        }

        res.setContentType(AjaxParameter.AJAX_CONTENT_TYPE);
        res.setCharacterEncoding(AjaxParameter.AJAX_CHARACTER_ENCODING);
        PrintWriter writer = res.getWriter();
        writer.write(new Gson().toJson(pagesAmount));
    }
}

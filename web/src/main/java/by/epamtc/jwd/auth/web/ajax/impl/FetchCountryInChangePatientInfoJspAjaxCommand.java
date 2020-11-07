package by.epamtc.jwd.auth.web.ajax.impl;

import by.epamtc.jwd.auth.model.ajax.AjaxCountry;
import by.epamtc.jwd.auth.model.ajax.AjaxParameter;
import by.epamtc.jwd.auth.service.ajax.AjaxFetchService;
import by.epamtc.jwd.auth.service.ajax.AjaxServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.ajax.AjaxCommand;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FetchCountryInChangePatientInfoJspAjaxCommand implements AjaxCommand {
    private AjaxServiceFactory ajaxServiceFactory = AjaxServiceFactory.getInstance();
    private AjaxFetchService ajaxFetchService = ajaxServiceFactory
            .getAjaxFetchService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String countryPart = req.getParameter(AjaxParameter.COUNTRY);
        List<AjaxCountry> countries = null;
        try {
            countries = ajaxFetchService.fetchCountries(countryPart);
        } catch (ServiceException e) {
            // TODO log4j
        }

        res.setContentType(AjaxParameter.AJAX_CONTENT_TYPE);
        res.setCharacterEncoding(AjaxParameter.AJAX_CHARACTER_ENCODING);
        PrintWriter writer = res.getWriter();
        writer.write(new Gson().toJson(countries));

    }
}

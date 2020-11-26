package by.epamtc.jwd.auth.web.util.ajax.impl;

import by.epamtc.jwd.auth.model.ajax.AjaxParameter;
import by.epamtc.jwd.auth.model.ajax.AjaxRegion;
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
import java.util.List;

public class FetchRegionInChangePatientInfoJspAjaxCommand implements AjaxCommand {
    private static final Logger logger = LoggerFactory.getLogger
            (FetchRegionInChangePatientInfoJspAjaxCommand.class);

    private AjaxServiceFactory ajaxServiceFactory = AjaxServiceFactory.getInstance();
    private AjaxFetchService ajaxFetchService = ajaxServiceFactory
            .getAjaxFetchService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String countryId = req.getParameter(AjaxParameter.COUNTRY_ID);
        String regionInput = req.getParameter(AjaxParameter.REGION);

        List<AjaxRegion> regions = null;
        try {
            regions = ajaxFetchService.fetchRegions(countryId, regionInput);
        } catch (ServiceException e) {
            logger.error("An error occurred while fetching from db regions\n" +
                    "with these params \"countryId: {}\", \"regionInput: {}\"",
                    countryId, regionInput, e);
        }

        res.setContentType(AjaxParameter.AJAX_CONTENT_TYPE);
        res.setCharacterEncoding(AjaxParameter.AJAX_CHARACTER_ENCODING);
        PrintWriter writer = res.getWriter();
        writer.write(new Gson().toJson(regions));
    }
}

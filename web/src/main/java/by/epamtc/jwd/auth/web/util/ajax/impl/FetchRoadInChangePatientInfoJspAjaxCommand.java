package by.epamtc.jwd.auth.web.util.ajax.impl;

import by.epamtc.jwd.auth.model.ajax_entity.AjaxParameter;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxRoad;
import by.epamtc.jwd.auth.service.ajax_service.AjaxFetchService;
import by.epamtc.jwd.auth.service.ajax_service.AjaxServiceFactory;
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

public class FetchRoadInChangePatientInfoJspAjaxCommand implements AjaxCommand {
    private static final Logger logger = LoggerFactory.getLogger
            (FetchRoadInChangePatientInfoJspAjaxCommand.class);

    private AjaxServiceFactory ajaxServiceFactory = AjaxServiceFactory.getInstance();
    private AjaxFetchService ajaxFetchService = ajaxServiceFactory
            .getAjaxFetchService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String settlementId = req.getParameter(AjaxParameter.SETTLEMENT_ID);
        String roadInput = req.getParameter(AjaxParameter.ROAD);

        List<AjaxRoad> roads = null;
        try {
            roads = ajaxFetchService.fetchRoads(settlementId, roadInput);
        } catch (ServiceException e) {
            logger.error("An error occurred while fetching from db roads\n" +
                            "with these params \"settlementId: {}\"," +
                            " \"roadInput: {}\"",
                    settlementId, roadInput, e);
        }

        res.setContentType(AjaxParameter.AJAX_CONTENT_TYPE);
        res.setCharacterEncoding(AjaxParameter.AJAX_CHARACTER_ENCODING);
        PrintWriter writer = res.getWriter();
        writer.write(new Gson().toJson(roads));
    }
}

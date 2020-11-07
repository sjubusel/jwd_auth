package by.epamtc.jwd.auth.web.ajax.impl;

import by.epamtc.jwd.auth.model.ajax.AjaxRoad;
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

public class FetchRoadInChangePatientInfoJspAjaxCommand implements AjaxCommand {
    private AjaxServiceFactory ajaxServiceFactory = AjaxServiceFactory.getInstance();
    private AjaxFetchService ajaxFetchService = ajaxServiceFactory
            .getAjaxFetchService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String settlementId = req.getParameter("hiddenSettlementId");
        String roadInput = req.getParameter("roadInput");

        List<AjaxRoad> roads = null;
        try {
            roads = ajaxFetchService.fetchRoads(settlementId, roadInput);
        } catch (ServiceException e) {
            // TODO log4j
        }

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        writer.write(new Gson().toJson(roads));
    }
}

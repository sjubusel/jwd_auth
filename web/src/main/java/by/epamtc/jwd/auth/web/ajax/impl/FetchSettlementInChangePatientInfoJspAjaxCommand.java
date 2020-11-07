package by.epamtc.jwd.auth.web.ajax.impl;

import by.epamtc.jwd.auth.model.ajax.AjaxSettlement;
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

public class FetchSettlementInChangePatientInfoJspAjaxCommand implements AjaxCommand {
    private AjaxServiceFactory ajaxServiceFactory = AjaxServiceFactory.getInstance();
    private AjaxFetchService ajaxFetchService = ajaxServiceFactory
            .getAjaxFetchService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String areaId = req.getParameter("hiddenAreaId");
        String settlementInput = req.getParameter("settlementInput");

        List<AjaxSettlement> settlements = null;
        try {
            settlements = ajaxFetchService.fetchSettlements(areaId, settlementInput);
        } catch (ServiceException e) {
            // TODO log4j
        }

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        writer.write(new Gson().toJson(settlements));
    }
}

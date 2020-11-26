package by.epamtc.jwd.auth.web.util.ajax.impl;

import by.epamtc.jwd.auth.model.ajax_entity.AjaxParameter;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxSettlement;
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

public class FetchSettlementInChangePatientInfoJspAjaxCommand implements AjaxCommand {
    private static final Logger logger = LoggerFactory.getLogger
            (FetchSettlementInChangePatientInfoJspAjaxCommand.class);

    private AjaxServiceFactory ajaxServiceFactory = AjaxServiceFactory.getInstance();
    private AjaxFetchService ajaxFetchService = ajaxServiceFactory
            .getAjaxFetchService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String areaId = req.getParameter(AjaxParameter.AREA_ID);
        String settlementInput = req.getParameter(AjaxParameter.SETTLEMENT);

        List<AjaxSettlement> settlements = null;
        try {
            settlements = ajaxFetchService.fetchSettlements(areaId, settlementInput);
        } catch (ServiceException e) {
            logger.error("An error occurred while fetching from db " +
                    "settlements\n with these params \"areaId: {}\"," +
                    " \"settlementInput: {}\"", areaId, settlementInput, e);
        }

        res.setContentType(AjaxParameter.AJAX_CONTENT_TYPE);
        res.setCharacterEncoding(AjaxParameter.AJAX_CHARACTER_ENCODING);
        PrintWriter writer = res.getWriter();
        writer.write(new Gson().toJson(settlements));
    }
}

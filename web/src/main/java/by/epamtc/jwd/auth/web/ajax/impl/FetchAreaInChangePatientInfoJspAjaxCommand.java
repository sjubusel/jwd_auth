package by.epamtc.jwd.auth.web.ajax.impl;

import by.epamtc.jwd.auth.model.ajax.AjaxArea;
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

public class FetchAreaInChangePatientInfoJspAjaxCommand implements AjaxCommand {
    private AjaxServiceFactory ajaxServiceFactory = AjaxServiceFactory.getInstance();
    private AjaxFetchService ajaxFetchService = ajaxServiceFactory
            .getAjaxFetchService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String regionId = req.getParameter("hiddenRegionId");
        String areaInput = req.getParameter("areaInput");

        List<AjaxArea> areas = null;
        try {
            areas = ajaxFetchService.fetchAreas(regionId, areaInput);
        } catch (ServiceException e) {
            // TODO log4j
        }

        write(areas, res);
    }

    //TODO create new class-singleton, which is to write
    private void write(Object areas, HttpServletResponse res)
            throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        writer.write(new Gson().toJson(areas));
    }
}

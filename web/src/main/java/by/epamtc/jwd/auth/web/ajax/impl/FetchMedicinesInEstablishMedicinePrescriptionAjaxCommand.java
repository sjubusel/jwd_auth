package by.epamtc.jwd.auth.web.ajax.impl;

import by.epamtc.jwd.auth.model.ajax.AjaxMedicine;
import by.epamtc.jwd.auth.model.ajax.AjaxParameter;
import by.epamtc.jwd.auth.service.ajax.AjaxFetchService;
import by.epamtc.jwd.auth.service.ajax.AjaxServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.ajax.AjaxCommand;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FetchMedicinesInEstablishMedicinePrescriptionAjaxCommand
        implements AjaxCommand {
    private static final Logger logger = LoggerFactory.getLogger
            (FetchMedicinesInEstablishMedicinePrescriptionAjaxCommand.class);

    private AjaxServiceFactory ajaxServiceFactory = AjaxServiceFactory.getInstance();
    private AjaxFetchService ajaxFetchService = ajaxServiceFactory
            .getAjaxFetchService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String medicinePart = req.getParameter(AjaxParameter.MEDICINE_PART);
        List<AjaxMedicine> medicineList = null;

        try {
            medicineList = ajaxFetchService.fetchMedicines(medicinePart);
        } catch (ServiceException e) {
            logger.error("An error occurred while fetching medicines from db \n" +
                    "with this param \"medicinePart: {}\" \n", medicinePart, e);
        }

        res.setContentType(AjaxParameter.AJAX_CONTENT_TYPE);
        res.setCharacterEncoding(AjaxParameter.AJAX_CHARACTER_ENCODING);
        PrintWriter writer = res.getWriter();
        writer.write(new Gson().toJson(medicineList));
    }
}

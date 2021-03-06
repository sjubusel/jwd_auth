package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.VisitService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToStaffAcceptMedicinePrescriptionCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (GoToStaffAcceptMedicinePrescriptionCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String medPrescriptionId = req.getParameter(AppParameter.PRESCRIPTION_ID);
        MedicinePrescription medicinePrescription = null;

        try {
            medicinePrescription = visitService.fetchVisitMedPrescriptionById(
                    medPrescriptionId, user);
        } catch (ServiceException e) {
            logger.error("An error while executing MedicinePrescription. " +
                    "Params{medPrescriptionId={}}", medPrescriptionId, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (medicinePrescription == null) {
            if (req.getAttribute(AppAttribute.REQUEST_ERROR) == null) {
                req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                        .REQUEST_ERROR_VALUE_VAL);
            }
        } else {
            req.setAttribute(AppAttribute.REQUEST_MEDICINE_PRESCRIPTION,
                    medicinePrescription);
        }

        req.setAttribute(AppParameter.PRESCRIPTION_ID, medPrescriptionId);
        req.getRequestDispatcher(CommandPath.SUBSTAFF_MED_PRESCR_EXECUTION_JSP)
                .forward(req, res);
    }
}

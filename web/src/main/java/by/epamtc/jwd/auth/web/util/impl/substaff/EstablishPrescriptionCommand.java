package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.med_info.DepartmentOrigin;
import by.epamtc.jwd.auth.model.med_info.Prescription;
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

public class EstablishPrescriptionCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (EstablishPrescriptionCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String visitId = req.getParameter(AppParameter.VISIT_ID);
        String prescriptionDescription = req.getParameter(AppParameter
                .PRESCRIPTION_DESCRIPTION);
        Prescription prescription = compilePrescription(prescriptionDescription,
                visitId);

        boolean isAdded;

        try {
            isAdded = visitService.establishPrescription(prescription, user);
        } catch (ServiceException e) {
            logger.error("An error while establishing a non-medicine " +
                            "prescription. Params{user={}, visitId={}, " +
                            "prescriptionDescription={}}", user, visitId,
                    prescriptionDescription, e);
            sendRedirectWithTechError(req, res, visitId);
            return;
        }

        if (!isAdded) {
            sendRedirectWithValidationError(req, res, visitId);
            return;
        }

        sendRedirectWithSuccessMessage(req, res, visitId);
    }

    private Prescription compilePrescription(String prescriptionDescription,
            String visitId) {
        Prescription prescription = new Prescription();
        prescription.setDepartmentOrigin(DepartmentOrigin.ADMISSION_DEPARTMENT);

        try {
            int visitNumber = Integer.parseInt(visitId);
            prescription.setOriginDocumentId(visitNumber);
        } catch (NumberFormatException e) {
            logger.error("An error while Prescription compilation." +
                            " Params{prescriptionDescription={}, visitId={}}",
                    prescriptionDescription, visitId, e);
        }

        prescription.setPrescriptionDescription(prescriptionDescription);


        return prescription;
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res, String visitId) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_ESTABLISH_PRESCRIPTION_ADD_RESULT_TECHERROR
                + visitId);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res, String visitStrId) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_ESTABLISH_PRESCRIPTION_ADD_RESULT_VAL_ERROR
                + visitStrId);
    }

    private void sendRedirectWithSuccessMessage(HttpServletRequest req,
            HttpServletResponse res, String visitStrId) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_ESTABLISH_PRESCRIPTION_ADD_RESULT_SUCCESS
                + visitStrId);
    }
}

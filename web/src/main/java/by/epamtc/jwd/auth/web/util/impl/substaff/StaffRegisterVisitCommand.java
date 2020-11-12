package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.user_info.TransportationStatus;
import by.epamtc.jwd.auth.model.visit_info.VisitReason;
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


public class StaffRegisterVisitCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (StaffRegisterVisitCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String personId = req.getParameter(AppParameter.VISIT_PERSON_ID);
        String visitReason = req.getParameter(AppParameter.VISIT_REASON);
        String visitReasonDescription = req.getParameter(AppParameter.VISIT_DESCRIPTION);
        String transportationStatus = req.getParameter(AppParameter.TRANSPORTATION_STATUS);

        boolean isRegistered = false;
        try {
            AdmissionDepartmentVisit hospVisit = compileAdmissionDepartmentVisit(
                    personId, visitReason, visitReasonDescription,
                    transportationStatus);
            isRegistered = visitService.registerVisit(hospVisit);
        } catch (ServiceException e) {
            logger.error("An error while registering a visit to a hospital." +
                            "Params=personId:{},visitReason:{},visitReasonDescription:{}," +
                            "transportationStatus:{}", personId, visitReason,
                    visitReasonDescription, transportationStatus, e);
            sendRedirectWithTechError(req, res);
            return;
        } catch (Exception e) {
            logger.error("An error caught from a request parameters." +
                            "Params=personId:{},visitReason:{},visitReasonDescription:{}," +
                            "transportationStatus:{}", personId, visitReason,
                    visitReasonDescription, transportationStatus, e);
            sendRedirectWithTechError(req, res);
            return;
        }

        if (!isRegistered) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res);
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_REGISTER_VISIT_ADD_RESULT_TECHERROR);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_REGISTER_VISIT_ADD_RESULT_VAL_ERROR);
    }

    private void sendRedirectWithSuccessMessage(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBSTAFF_GO_TO_REGISTER_VISIT_ADD_RESULT_SUCCESS);
    }

    private AdmissionDepartmentVisit compileAdmissionDepartmentVisit(String
            personId, String visitReason, String visitReasonDescription,
            String transStatus) {
        AdmissionDepartmentVisit visit = new AdmissionDepartmentVisit();
        visit.setPatientShortInfo(personId);
        visit.setVisitReason(VisitReason.valueOf(visitReason));
        visit.setPatientVisitDescriptionInfo(visitReasonDescription);
        visit.setTransportationStatus(TransportationStatus.valueOf(transStatus));
        return visit;
    }
}

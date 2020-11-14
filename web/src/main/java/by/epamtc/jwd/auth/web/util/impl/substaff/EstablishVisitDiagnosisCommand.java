package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
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

public class EstablishVisitDiagnosisCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (EstablishVisitDiagnosisCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String visitStrId = req.getParameter(AppParameter.VISIT_ID);
        String diseaseId = req.getParameter(AppParameter.DISEASE_ID);
        String diagnosisDescription = req.getParameter(AppParameter.DIAGNOSIS_DESCRIPTION);
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiseaseInfo(diseaseId);
        diagnosis.setDiagnosisDescription(diagnosisDescription);

        boolean isChanged;

        try {
            isChanged = visitService.establishDiagnosis(diagnosis, visitStrId,
                    user);
        } catch (ServiceException e) {
            logger.error("An error while changing patient's complaints.\n" +
                            "Params{user={}, visitStrId={}, diseaseId={}}", user,
                    visitStrId, diseaseId, e);
//            sendRedirectWithTechError(req, res, visitId);
            return;
        }

    }
}

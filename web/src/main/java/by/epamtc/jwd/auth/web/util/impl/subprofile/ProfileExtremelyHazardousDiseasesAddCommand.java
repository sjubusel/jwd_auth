package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase;
import by.epamtc.jwd.auth.service.ProfileService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class ProfileExtremelyHazardousDiseasesAddCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (ProfileExtremelyHazardousDiseasesAddCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProfileService profileService = serviceFactory.getProfileService();


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String diseaseId = req.getParameter(AppParameter.DISEASE_ID);
        String detectionDate = req.getParameter(AppParameter.HAZARDOUS_DETECTION_DATE);
        String caseDescription = req.getParameter(AppParameter.CASE_DESCRIPTION);
        ExtremelyHazardousDiseaseCase hazardousDiseaseCase
                = compileExtremelyHazardousDiseaseCase(diseaseId, detectionDate,
                caseDescription);

        boolean isAdded;

        try {
            isAdded = profileService.addExtremelyHazardousDisease(
                    hazardousDiseaseCase, user);
        } catch (ServiceException e) {
            logger.error("An error while adding extremely hazardous disease\n" +
                    "to a patient (user) with param: \"{}\".\n" +
                    "AuthUser: \"{user}\"", diseaseId, e);
            sendRedirectWithTechError(req, res);
            return;
        }

        if (!isAdded) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res);
    }

    private ExtremelyHazardousDiseaseCase compileExtremelyHazardousDiseaseCase(
            String diseaseId, String detectionDate, String caseDescription) {
        ExtremelyHazardousDiseaseCase hCase = new ExtremelyHazardousDiseaseCase();
        hCase.setDiseaseDescription(diseaseId);
        hCase.setDetectionDate(LocalDate.parse(detectionDate));
        hCase.setCaseDescription(caseDescription);
        return hCase;
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_EXTREMELY_HAZARDOUS_DISEASES_ADD_RESULT_TECH_ERROR);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_EXTREMELY_HAZARDOUS_DISEASES_ADD_RESULT_VALID_ERROR);
    }

    private void sendRedirectWithSuccessMessage(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_EXTREMELY_HAZARDOUS_DISEASES_ADD_SUCCESSFUL_RESULT);
    }
}

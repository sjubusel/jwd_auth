package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.user_info.AllergicMedicineReaction;
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

public class ProfileAllergicReactionsMedicineAddCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (ProfileAllergicReactionsMedicineAddCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProfileService profileService = serviceFactory.getProfileService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);

        String medicineTypeId = req.getParameter(AppParameter.MEDICINE_TYPE_ID);
        String detectionDate = req.getParameter(AppParameter
                .MEDICINE_DETECTION_DATE);
        String description = req.getParameter(AppParameter
                .MEDICINE_DETECTION_DESCRIPTION);
        AllergicMedicineReaction medicineReaction = compileChangingAllergicReaction
                (medicineTypeId, detectionDate, description);


        boolean isAdded;

        try {
            isAdded = profileService.addAllergicMedicineReaction(medicineReaction,
                    user);
        } catch (ServiceException e) {
            logger.error("An error while adding allergic medicine reaction \n" +
                    "to a patient (user) with param: \"{}\".\n" +
                    "AuthUser: \"{user}\"", medicineReaction, e);
            sendRedirectWithTechError(req, res);
            return;
        }

        if (!isAdded) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res);
    }

    private AllergicMedicineReaction compileChangingAllergicReaction
            (String medicineId, String detectionDate, String description) {
        AllergicMedicineReaction medicineReaction = new AllergicMedicineReaction();
        medicineReaction.setMedicineDescription(medicineId);
        medicineReaction.setDetectionDate(LocalDate.parse(detectionDate));
        medicineReaction.setAllergicReaction(description);
        return medicineReaction;
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_MEDICINE_RESULT_TECH_ERROR);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_MEDICINE_RESULT_VALID_ERROR);
    }

    private void sendRedirectWithSuccessMessage(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_MEDICINE_SUCCESSFUL_RESULT);
    }
}

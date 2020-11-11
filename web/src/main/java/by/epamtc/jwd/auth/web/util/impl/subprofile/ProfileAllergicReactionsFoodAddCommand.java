package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.user_info.AllergicFoodReaction;
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

public class ProfileAllergicReactionsFoodAddCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (ProfileAllergicReactionsFoodAddCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProfileService profileService = serviceFactory.getProfileService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);

        String foodTypeId = req.getParameter(AppParameter.FOOD_TYPE_ID);
        String detectionDate = req.getParameter(AppParameter
                .FOOD_DETECTION_DATE);
        String description = req.getParameter(AppParameter
                .FOOD_DETECTION_DESCRIPTION);
        AllergicFoodReaction foodReaction = compileChangingAllergicReaction
                (foodTypeId, detectionDate, description);


        boolean isAdded;

        try {
            isAdded = profileService.addAllergicFoodReaction(foodReaction, user);
        } catch (ServiceException e) {
            logger.error("An error while adding allergic food reaction \n" +
                    "to a patient (user) with param: \"{}\".\n" +
                    "AuthUser: \"{user}\"", foodReaction, e);
            sendRedirectWithTechError(req, res);
            return;
        }

        if (!isAdded) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res);
    }

    private AllergicFoodReaction compileChangingAllergicReaction(String foodTypeId,
            String detectionDate, String description) {
        AllergicFoodReaction allergicFoodReaction = new AllergicFoodReaction();
        allergicFoodReaction.setFoodTypeInfo(foodTypeId);
        allergicFoodReaction.setDetectionDate(LocalDate.parse(detectionDate));
        allergicFoodReaction.setAllergicDescription(description);
        return allergicFoodReaction;
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_FOOD_RESULT_TECH_ERROR);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_FOOD_RESULT_VALID_ERROR);
    }

    private void sendRedirectWithSuccessMessage(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_FOOD_SUCCESSFUL_RESULT);
    }
}

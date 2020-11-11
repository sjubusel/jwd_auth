package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.user_info.AllergicReactionsInfo;
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

public class GoToProfileAllergicReactionsCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (GoToProfileAllergicReactionsCommand.class);

    private ServiceFactory factory = ServiceFactory.getInstance();
    private ProfileService profileService = factory.getProfileService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession()
                .getAttribute(AppAttribute.SESSION_AUTH_USER);
        AllergicReactionsInfo allergicReactionsInfo = null;

        try {
            allergicReactionsInfo = profileService.fetchAllergicReactionsInfo(user);
        } catch (ServiceException e) {
            logger.error("An error while fetching of allergic reactions " +
                    "information. AuthUser: \"{}\"", user, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (allergicReactionsInfo == null) {
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_VAL);
        } else {
            if (allergicReactionsInfo.getAllergicFoodReactions().size() > 0) {
                req.setAttribute(AppAttribute.REQUEST_ALLERGIC_FOOD_REACTIONS,
                        allergicReactionsInfo.getAllergicFoodReactions());
            }

            if (allergicReactionsInfo.getAllergicMedicineReactions().size() > 0) {
                req.setAttribute(AppAttribute.REQUEST_ALLERGIC_MEDICINE_REACTIONS,
                        allergicReactionsInfo.getAllergicMedicineReactions());
            }
        }

        String foodAddResult = req.getParameter(AppParameter.ADD_FOOD_RESULT);
        if (foodAddResult != null) {
            req.setAttribute(AppParameter.ADD_FOOD_RESULT, foodAddResult);
        }

        String medicineAddResult = req.getParameter(AppParameter.ADD_MEDICINE_RESULT);
        if (medicineAddResult != null) {
            req.setAttribute(AppParameter.ADD_MEDICINE_RESULT, medicineAddResult);
        }

        req.getRequestDispatcher(CommandPath.SUBPROFILE_ALLERGIC_REACTIONS_JSP)
                .forward(req, res);
    }
}

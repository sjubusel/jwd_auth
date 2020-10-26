package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.service.AuthUserService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.AuthDataValidationServiceException;
import by.epamtc.jwd.auth.service.exception.LoginValidationServiceException;
import by.epamtc.jwd.auth.service.exception.PasswordValidationServiceException;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.model.constant.AppAttributes;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPaths;
import by.epamtc.jwd.auth.web.util.RegistrationInfoCompiler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AuthUserService authUserService = serviceFactory.getAuthUserService();
    private RegistrationInfoCompiler regInfCompiler = RegistrationInfoCompiler
            .getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String login = req.getParameter(AppParameter.LOGIN);
        String password = req.getParameter(AppParameter.PASSWORD);
        String email = req.getParameter(AppParameter.EMAIL);
        String phoneNumberCountryCode = req.getParameter(AppParameter
                .NUMBER_COUNTRY_CODE);
        String phoneNumberInnerCode = req.getParameter(AppParameter
                .NUMBER_INNER_CODE);
        String phoneNumberInnerNumber = req.getParameter(AppParameter
                .INNER_NUMBER);
        String firstName = req.getParameter(AppParameter.FIRST_NAME);
        String middleName = req.getParameter(AppParameter.MIDDLE_NAME);
        String lastName = req.getParameter(AppParameter.LAST_NAME);
        String birthday = req.getParameter(AppParameter.BIRTHDAY);
        String gender = req.getParameter(AppParameter.GENDER);

        AuthUser user;
        RegistrationInfo regInfo = regInfCompiler.compileRegInfo(login, password,
                email, phoneNumberCountryCode, phoneNumberInnerCode,
                phoneNumberInnerNumber, firstName, middleName, lastName,
                birthday, gender);
        // TODO Change register parameters
        try {
            user = authUserService.register(login, password);
        } catch (AuthDataValidationServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.REGISTER_AUTH_ERROR);
            return;
        } catch (LoginValidationServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.REGISTER_LOGIN_ERROR);
            return;
        } catch (PasswordValidationServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.REGISTER_PASSWORD_ERROR);
            return;
        } catch (ServiceException e) {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.REGISTER_TECH_ERROR);
            return;
        }

        if (user != null) {
            req.getSession().setAttribute(AppAttributes.SESSION_AUTH_DATA, user);
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.PROFILE_GET);
        } else {
            res.sendRedirect(req.getContextPath()
                    + CommandPaths.REGISTER_DUPLICATE_ERROR);
        }
    }
}


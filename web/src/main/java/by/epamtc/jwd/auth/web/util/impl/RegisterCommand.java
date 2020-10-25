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
import by.epamtc.jwd.auth.model.constant.AppParameters;
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
        String login = req.getParameter(AppParameters.LOGIN);
        String password = req.getParameter(AppParameters.PASSWORD);
        String email = req.getParameter("email");
        String phoneNumberCountryCode = req.getParameter("phoneNumberCountryCode");
        String phoneNumberInnerCode = req.getParameter("phoneNumberInnerCode");
        String phoneNumberInnerNumber = req.getParameter("phoneNumberInnerNumber");
        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String lastName = req.getParameter("lastName");
        String birthday = req.getParameter("birthday");
        String gender = req.getParameter("gender");

        AuthUser user;
        RegistrationInfo regInfo = regInfCompiler.compileRegInfo(login, password,
                email, phoneNumberCountryCode, phoneNumberInnerCode,
                phoneNumberInnerNumber, firstName, middleName, lastName,
                birthday, gender);

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


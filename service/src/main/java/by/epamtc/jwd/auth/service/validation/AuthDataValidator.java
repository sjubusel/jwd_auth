package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.service.exception.AuthDataValidationServiceException;
import by.epamtc.jwd.auth.service.exception.LoginValidationServiceException;
import by.epamtc.jwd.auth.service.exception.PasswordValidationServiceException;
import by.epamtc.jwd.auth.service.exception.ValidationServiceException;

public class AuthDataValidator {
    public boolean validateLogin(String login) {
        return false;
    }

    public boolean validatePassword(String password) {
        return false;
    }

    public boolean isAuthDataValid(String login, String password)
            throws ValidationServiceException {
        if (validateLogin(login)) {
            if (validatePassword(password)) {
                throw new AuthDataValidationServiceException();
            }
            throw new LoginValidationServiceException();
        }
        if (validatePassword(password)) {
            throw new PasswordValidationServiceException();
        }
        return true;
    }
}

package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.service.exception.AuthDataValidationServiceException;
import by.epamtc.jwd.auth.service.exception.LoginValidationServiceException;
import by.epamtc.jwd.auth.service.exception.PasswordValidationServiceException;
import by.epamtc.jwd.auth.service.exception.ValidationServiceException;

public class AuthDataValidator {
    public boolean validateLogin(String login) {
        return (login.length() >= 3) && (login.length() <= 20)
                && login.matches("[A-Za-z.-_0-9]+");
    }

    public boolean validatePassword(String password) {
        return (password.length() >= 6)
                && password.matches("[A-Za-zА-ЯаяЁё0-9!?@#$%^&*()\\-_+:;,.\"']+");
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

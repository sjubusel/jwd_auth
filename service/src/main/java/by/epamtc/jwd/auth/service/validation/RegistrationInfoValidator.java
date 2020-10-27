package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.Gender;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.service.exception.ValidationServiceException;

import java.time.LocalDate;

/**
 * ValidationServiceExceptions are thrown because it is an exceptional case
 * and by default the same validation is performed on the client-side
 * (input pattern activity)
 */
public class RegistrationInfoValidator {
    public boolean isRegistrationInfoValid(RegistrationInfo regInfo)
            throws ValidationServiceException {
        if (!isLoginValid(regInfo.getLogin())) {
            throw new ValidationServiceException("INVALID LOGIN FORMAT");
        }
        if (!isPasswordValid(regInfo.getPassword())) {
            throw new ValidationServiceException("INVALID PASSWORD FORMAT");
        }
        if (!isEmailValid(regInfo.getEmail())) {
            throw new ValidationServiceException("INVALID EMAIL FORMAT");
        }
        if (!isPhoneValid(regInfo.getPhoneNumber())) {
            throw new ValidationServiceException("INVALID PHONE FORMAT");
        }
        if (!isFirstNameValid(regInfo.getFirstName())) {
            throw new ValidationServiceException("INVALID FIRST NAME FORMAT");
        }
        if (!isMiddleNameValid(regInfo.getMiddleName())) {
            throw new ValidationServiceException("INVALID MIDDLE NAME FORMAT");
        }
        if (!isLastNameValid(regInfo.getLastName())) {
            throw new ValidationServiceException("INVALID LAST NAME FORMAT");
        }
        if (!isBirthdayDateValid(regInfo.getBirthday())) {
            throw new ValidationServiceException("INVALID BIRTHDAY FORMAT");
        }
        if (!isGenderValid(regInfo.getGender())) {
            throw new ValidationServiceException("INVALID GENDER FORMAT");
        }
        return true;
    }

    public boolean isLoginValid(String login) {
        if (login == null) {
            return false;
        }
        return (login.length() >= AppConstant.LOGIN_MIN_LENGTH)
                && (login.length() <= AppConstant.LOGIN_MAX_LENGTH)
                && login.matches(RegistrationInfoPattern.LOGIN);
    }

    public boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        return (password.length() >= AppConstant.PASSWORD_MIN_LENGTH)
                && (password.length() <= AppConstant.PASSWORD_MAX_LENGTH)
                && password.matches(RegistrationInfoPattern.PASSWORD);
    }

    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        return email.matches(RegistrationInfoPattern.EMAIL);
    }

    private boolean isPhoneValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.matches(RegistrationInfoPattern.PHONE_NUMBER);
    }

    private boolean isFirstNameValid(String firstName) {
        if (firstName == null) {
            return false;
        }
        return firstName.matches(RegistrationInfoPattern.ANY_NAME);
    }

    private boolean isMiddleNameValid(String middleName) {
        if (middleName == null) {
            return true;
        }
        return middleName.matches(RegistrationInfoPattern.ANY_NAME);
    }

    private boolean isLastNameValid(String lastName) {
        if (lastName == null) {
            return false;
        }
        return lastName.matches(RegistrationInfoPattern.ANY_NAME);
    }

    private boolean isBirthdayDateValid(LocalDate birthday) {
        if (birthday == null) {
            return false;
        }
        LocalDate birthdayOfOlderPersonAlive = LocalDate.of(AppConstant
                        .OLDEST_PERSON_BIRTH_YEAR,
                AppConstant.OLDEST_PERSON_BIRTH_MONTH,
                AppConstant.OLDEST_PERSON_BIRTH_DAY);
        return (birthday.compareTo(birthdayOfOlderPersonAlive) >= 0)
                && (birthday.compareTo(LocalDate.now()) <= 0);
    }

    private boolean isGenderValid(Gender currentGender) {
        if (currentGender == null) {
            return false;
        }
        Gender[] availableGenders = Gender.values();
        for (Gender gender : availableGenders) {
            if (currentGender.equals(gender)) {
                return true;
            }
        }
        return false;
    }
}

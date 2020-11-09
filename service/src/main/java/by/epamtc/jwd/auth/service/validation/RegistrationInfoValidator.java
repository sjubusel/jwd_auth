package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.AuthenticationInfo;
import by.epamtc.jwd.auth.model.user_info.Gender;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.constant.AppConstant;

import java.time.LocalDate;

public class RegistrationInfoValidator {
    public boolean isRegistrationInfoValid(RegistrationInfo regInfo) {
        if (!isLoginValid(regInfo.getLogin())) {
            return false;
        }
        if (!isPasswordValid(regInfo.getPassword())) {
            return false;
        }
        if (!isEmailValid(regInfo.getEmail())) {
            return false;
        }
        if (!isPhoneValid(regInfo.getPhoneNumber())) {
            return false;
        }
        if (!isFirstNameValid(regInfo.getFirstName())) {
            return false;
        }
        if (!isMiddleNameValid(regInfo.getMiddleName())) {
            return false;
        }
        if (!isLastNameValid(regInfo.getLastName())) {
            return false;
        }
        if (!isBirthdayDateValid(regInfo.getBirthday())) {
            return false;
        }
        return isGenderValid(regInfo.getGender());
    }

    public boolean isAuthenticationInfoValid(AuthenticationInfo authenticationInfo) {
        if (!isLoginValid(authenticationInfo.getLogin())) {
            return false;
        }
        return isPasswordValid(authenticationInfo.getPassword());
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

    public boolean isEmailValid(String email) {
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

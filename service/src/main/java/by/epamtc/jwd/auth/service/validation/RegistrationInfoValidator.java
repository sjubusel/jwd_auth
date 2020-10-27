package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.Gender;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.constant.UtilConstant;

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

    public boolean isLoginValid(String login) {
        if (login == null) {
            return false;
        }
        return (login.length() >= UtilConstant.LOGIN_MIN_LENGTH)
                && (login.length() <= UtilConstant.LOGIN_MAX_LENGTH)
                && login.matches(RegistrationInfoPattern.LOGIN);
    }

    public boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        return (password.length() >= UtilConstant.PASSWORD_MIN_LENGTH)
                && (password.length() <= UtilConstant.PASSWORD_MAX_LENGTH)
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
        LocalDate birthdayOfOlderPersonAlive = LocalDate.of(UtilConstant
                        .OLDEST_PERSON_BIRTH_YEAR,
                UtilConstant.OLDEST_PERSON_BIRTH_MONTH,
                UtilConstant.OLDEST_PERSON_BIRTH_DAY);
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

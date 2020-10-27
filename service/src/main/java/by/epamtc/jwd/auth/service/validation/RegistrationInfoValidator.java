package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.Gender;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;

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
        return (login.length() >= 3) && (login.length() <= 20)
                && login.matches(RegistrationInfoPattern.LOGIN);
    }

    public boolean isPasswordValid(String password) {
        // TODO add about length in properties PASS_INPUT_DESCRIPTION
        if (password == null) {
            return false;
        }
        return (password.length() >= 6) && (password.length() <= 255) &&
                password.matches(RegistrationInfoPattern.PASSWORD);
    }

    // TODO add a description in a property file
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

    // TODO add a description in a property file
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
        // TODO make Const against a magic value
        LocalDate birthdayOfOlderPersonAlive = LocalDate.of(1903, 1, 2);
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

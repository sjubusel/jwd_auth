package by.epamtc.jwd.auth.web.util;

import by.epamtc.jwd.auth.model.auth_info.Gender;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.UtilConstant;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistrationInfoCompiler {
    private static volatile RegistrationInfoCompiler instance;

    private RegistrationInfoCompiler() {
    }

    public static RegistrationInfoCompiler getInstance() {
        RegistrationInfoCompiler localInstance = instance;
        if (localInstance == null) {
            synchronized (RegistrationInfoCompiler.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance
                            = new RegistrationInfoCompiler();
                }
            }
        }
        return localInstance;
    }

    public RegistrationInfo compileRegInfo(HttpServletRequest req) {
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

        return compileRegInfo(login, password, email, phoneNumberCountryCode,
                phoneNumberInnerCode, phoneNumberInnerNumber, firstName,
                middleName, lastName, birthday, gender);
    }

    public RegistrationInfo compileRegInfo(String login, String pass,
            String email, String phoneCountryCode, String phoneInnerCode,
            String phoneInnerNumber, String firstName, String middleName,
            String lastName, String birthday, String gender) {
        String phoneNumber = compilePhoneNumber(phoneCountryCode, phoneInnerCode,
                phoneInnerNumber);
        LocalDate birthdayDate = compileBirthdayDate(birthday);
        Gender genderEnum = compileGenderEnum(gender);
        return new RegistrationInfo(login, pass, email, phoneNumber, firstName,
                middleName, lastName, birthdayDate, genderEnum);
    }

    private String compilePhoneNumber(String phoneCountryCode,
            String phoneInnerCode, String phoneInnerNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(phoneCountryCode)
                .append(UtilConstant.ONE_WHITESPACE)
                .append(UtilConstant.OPENING_PARENTHESIS)
                .append(phoneInnerCode)
                .append(UtilConstant.CLOSING_PARENTHESIS)
                .append(UtilConstant.ONE_WHITESPACE)
                .append(phoneInnerNumber);
        return new String(stringBuilder);
    }

    private LocalDate compileBirthdayDate(String birthday) {
        LocalDate birthdayDate = null;
        try {
            birthdayDate = LocalDate.parse(birthday, DateTimeFormatter.ISO_DATE);
        } catch (Exception e) {
            // TODO add log4j announcement, because this situation is available only when someone wants to hack the system
            e.printStackTrace();
        }
        return birthdayDate;
    }

    private Gender compileGenderEnum(String gender) {
        Gender[] availableGenders = Gender.values();
        for (Gender genderEnum : availableGenders) {
            if (gender.equals(genderEnum.getGenderName())) {
                return genderEnum;
            }
        }
        return null;
    }
}

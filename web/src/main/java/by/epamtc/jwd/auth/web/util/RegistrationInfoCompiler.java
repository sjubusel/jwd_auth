package by.epamtc.jwd.auth.web.util;

import by.epamtc.jwd.auth.model.auth_info.Gender;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;

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

    public RegistrationInfo compileRegInfo(String login, String pass,
            String email, String phoneCountryCode, String phoneInnerCode,
            String phoneInnerNumber, String firstName, String middleName,
            String lastName, String birthday, String gender) {
        String phoneNumber = compilePhoneNumber(phoneCountryCode, phoneInnerCode,
                phoneInnerNumber);
        LocalDate birthdayDate = compileBirthdayDate(birthday);
        Gender genderEnum = compileGenderEnum(gender);
        return new RegistrationInfo();
    }

    public String compilePhoneNumber(String phoneCountryCode,
            String phoneInnerCode, String phoneInnerNumber) {

        return null;
    }

    private LocalDate compileBirthdayDate(String birthday) {

        return null;
    }

    private Gender compileGenderEnum(String gender) {
        return null;
    }
}

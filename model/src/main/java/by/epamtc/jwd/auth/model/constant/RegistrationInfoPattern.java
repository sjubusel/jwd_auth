package by.epamtc.jwd.auth.model.constant;

import java.util.Locale;
import java.util.ResourceBundle;

public class RegistrationInfoPattern {
    public static final String LOGIN;
    public static final String PASSWORD;
    public static final String EMAIL;
    public static final String PHONE_NUMBER;
    public static final String ANY_NAME;

    public static final String SERIES;
    public static final String ANY_LATIN_NAME;
    public static final String DIGITS;
    public static final String PERSONAL_NUMBER;
    public static final String PLACE_OF_ORIGIN;
    public static final String ISSUE_AUTHORITY;


    static {
        ResourceBundle bundle = ResourceBundle.getBundle("registrationRegExp",
                Locale.US);
        LOGIN = bundle.getString("login");
        PASSWORD = bundle.getString("password");
        EMAIL = bundle.getString("email");
        PHONE_NUMBER = bundle.getString("phoneNumber");
        ANY_NAME = bundle.getString("anyName");

        SERIES = bundle.getString("profileSubMenu.changePatientInfo.seriesPattern");
        ANY_LATIN_NAME = bundle.getString("profileSubMenu.changePatientInfo.latinHolderNamePattern");
        DIGITS = bundle.getString("digits");
        PERSONAL_NUMBER = bundle.getString("profileSubMenu.changePatientInfo.idDocPersonalNumberPattern");
        PLACE_OF_ORIGIN = bundle.getString("profileSubMenu.changePatientInfo.idDocPlaceOfOriginPattern");
        ISSUE_AUTHORITY = bundle.getString("profileSubMenu.changePatientInfo.idDocIssueAuthorityPattern");
    }
}

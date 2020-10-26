package by.epamtc.jwd.auth.model.constant;

import java.util.Locale;
import java.util.ResourceBundle;

public class RegistrationInfoPattern {
    public static final String LOGIN;
    public static final String PASSWORD;
    public static final String EMAIL;
    public static final String PHONE_NUMBER;
    public static final String ANY_NAME;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("registrationRegExp",
                Locale.US);
        LOGIN = bundle.getString("login");
        PASSWORD = bundle.getString("password");
        EMAIL = bundle.getString("email");
        PHONE_NUMBER = bundle.getString("phoneNumber");
        ANY_NAME = bundle.getString("anyName");
    }
}

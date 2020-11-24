package by.epamtc.jwd.auth.model.constant;

public final class AppConstant {
    public static final String ONE_WHITESPACE = " ";
    public static final String OPENING_PARENTHESIS = "(";
    public static final String CLOSING_PARENTHESIS = ")";
    public static final String SEMICOLON = ";";
    public static final String KEY_VALUE_PAIR_DELIMITER = "=";
    public static final String QUOTE_MARK = "\"";
    public static final String EMPTY = "";
    public static final String REGEX_DOT = "\\.";
    public static final String UNDERSCORE = "_";
    public static final String DOT = ".";
    public static final String COLON = ":";
    public static final String COMMA = ",";


    public static final String SIMPLE_LOCAL_DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    public static final String MG_SUFFIX = "мг";
    public static final String SLASH = "/";
    public static final String ML_SUFFIX = "мл";


    public static final int LOGIN_MIN_LENGTH = 3;
    public static final int LOGIN_MAX_LENGTH = 20;
    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 255;
    public static final int OLDEST_PERSON_BIRTH_YEAR = 1903;
    public static final int OLDEST_PERSON_BIRTH_MONTH = 1;
    public static final int OLDEST_PERSON_BIRTH_DAY = 2;
    public static final int DUPLICATE_AUTH_USER_LOGIN_ID = -1;
    public static final int DUPLICATE_AUTH_USER_EMAIL_ID = -1;
    public static final int AUTH_USER_STANDARD_INT_VALUE = 0;
    public static final int STAFF_WHICH_CAN_TREAT = 8;
    public static final int APPLIEING_MEDICINE_STAFF = 5;
    public static final int PARAMEDICAL_STAFF = 3;
    public static final int STAFF_WHICH_CAN_VIEW_MEDICINE_DOCUMENTS = 7;
    public static final String DEFAULT_PAGE_NUMBER = "1";
    public static final int REFERENCES_PER_PAGE = 10;


    private AppConstant() {
    }
}

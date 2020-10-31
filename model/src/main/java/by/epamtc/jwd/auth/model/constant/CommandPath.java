package by.epamtc.jwd.auth.model.constant;

public final class CommandPath {
    public static final String LOGIN_GET = "/main?command=go-to-login";
    public static final String PROFILE_GET = "/profile?command=go-to-profile";
    public static final String LOGIN_TECH_ERROR = "/main?command=go-to-login&error=tech";
    public static final String LOGIN_SIMPLE_ERROR = "/main?command=go-to-login&error=simple";
    public static final String REGISTER_TECH_ERROR = "/main?command=go-to-register&error=tech";
    public static final String REG_INFO_VAL_ERROR = "/main?command=go-to-register&error=val";
    public static final String REGISTER_DUPLICATE_ERROR = "/main?command=go-to-register&error=duplicate";
    public static final String REGISTER_DUPLICATE_ERROR_BY_LOGIN = "/main?command=go-to-register&error=duplicateLogin";
    public static final String REGISTER_DUPLICATE_ERROR_BY_EMAIL = "/main?command=go-to-register&error=duplicateEmail";

    public static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    public static final String PROFILE_JSP = "/WEB-INF/jsp/profile.jsp";
    public static final String REGISTER_JSP = "/WEB-INF/jsp/register.jsp";
    public static final String MAIN_JSP = "/WEB-INF/jsp/main.jsp";
    public static final String ABOUT_US_JSP = "/WEB-INF/jsp/aboutUs.jsp";
    public static final String CONTACTS_JSP = "/WEB-INF/jsp/contacts.jsp";
    public static final String NEWS_JSP = "/WEB-INF/jsp/news.jsp";
    public static final String PATIENTS_JSP = "/WEB-INF/jsp/patients.jsp";
    public static final String STAFF_JSP = "/WEB-INF/jsp/staff.jsp";

    public static final String SUBPROFILE_ALLERGIC_REACTIONS_JSP = "/WEB-INF/jsp/subprofile/allergicReactions.jsp";
    public static final String SUBPROFILE_CHANGE_EMAIL_JSP = "";
    public static final String SUBPROFILE_CHANGE_PASSWORD_JSP = "";
    public static final String SUBPROFILE_CHANGE_PATIENT_INFO_JSP = "";
    public static final String SUBPROFILE_CHANGE_PHOTO_JSP = "";
    public static final String SUBPROFILE_CHANGE_STAFF_INFO_JSP = "";
    public static final String SUBPROFILE_CHANGE_STAFF_PHOTO_JSP = "";
    public static final String SUBPROFILE_EXTREMELY_HAZARDOUS_DISEASES_JSP = "";
    public static final String SUBPROFILE_STAFF_HISTORY_JSP = "";
    public static final String SUBPROFILE_STAFF_INFO_JSP = "";

    private CommandPath() {
    }
}

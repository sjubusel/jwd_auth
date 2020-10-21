package by.epamtc.jwd.auth.model.constant;

public final class CommandPaths {
    public static final String LOGIN_GET = "/main?command=go-to-login";
    public static final String PROFILE_GET = "/profile?command=go-to-profile";
    public static final String LOGIN_TECH_ERROR = "/main?command=go-to-login&error=tech";
    public static final String LOGIN_SIMPLE_ERROR = "/main?command=go-to-login&error=simple";
    public static final String REGISTER_AUTH_ERROR = "/main?command=go-to-register&error=auth-data";
    public static final String REGISTER_LOGIN_ERROR = "/main?command=go-to-register&error=login";
    public static final String REGISTER_PASSWORD_ERROR = "/main?command=go-to-register&error=pass";
    public static final String REGISTER_TECH_ERROR = "/main?command=go-to-register&error=tech";
    public static final String REGISTER_DUPLICATE_ERROR = "/main?command=go-to-register&error=duplicate";

    public static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    public static final String PROFILE_JSP = "/WEB-INF/jsp/profile.jsp";
    public static final String REGISTER_JSP = "/WEB-INF/jsp/register.jsp";
    public static final String MAIN_JSP = "/WEB-INF/jsp/main.jsp";
    public static final String ABOUT_US_JSP = "/WEB-INF/jsp/aboutUs.jsp";
    public static final String CONTACTS_JSP = "/WEB-INF/jsp/contacts.jsp";
    public static final String NEWS_JSP = "/WEB-INF/jsp/news.jsp";
    public static final String PATIENTS_JSP = "/WEB-INF/jsp/patients.jsp";
    public static final String STAFF_JSP = "/WEB-INF/jsp/staff.jsp";

    private CommandPaths() {
    }
}

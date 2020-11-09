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

    // TODO delete???
    public static final String PROFILE_TECH_ERROR = "/profile?command=go-to-profile&error=tech";
    // TODO delete???
    public static final String PROFILE_AUTH_USER_VALIDATION_ERROR = "/profile?command=go-to-profile&error=val";

    public static final String SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_TECH_ERROR
            = "/profile?command=go-to-profile-change-patient-info&photoUpload=techError";
    public static final String SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_VALIDATION_ERROR
            = "/profile?command=go-to-profile-change-patient-info&photoUpload=validationError";
    public static final String SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_INCORRECT_FILE_NAME
            = "/profile?command=go-to-profile-change-patient-info&photoUpload=incorrectFileName";
    public static final String SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_SUCCESS_UPLOAD
            = "/profile?command=go-to-profile-change-patient-info&photoUpload=success";

    public static final String SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_CHANGE_RESULT_TECH_ERROR
            = "/profile?command=go-to-profile-change-patient-info&changeResult=techError";
    public static final String SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_CHANGE_RESULT_VALID_ERROR
            = "/profile?command=go-to-profile-change-patient-info&changeResult=validationError";
    public static final String SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_CHANGE_RESULT_SUCCESS
            = "/profile?command=go-to-profile-change-patient-info&changeResult=success";

    public static final String SUBPROFILE_GO_TO_EMAIL_CHANGE_RESULT_TECH_ERROR
            = "/profile?command=go-to-profile-email-change&changeResult=techError";
    public static final String SUBPROFILE_GO_TO_EMAIL_CHANGE_RESULT_VALID_ERROR
            = "/profile?command=go-to-profile-email-change&changeResult=validationError";
    public static final String SUBPROFILE_GO_TO_EMAIL_CHANGE_RESULT_DUPLICATE_ERROR
            = "/profile?command=go-to-profile-email-change&changeResult=duplicateError";
    public static final String SUBPROFILE_GO_TO_EMAIL_CHANGE_RESULT_ILLEGAL_PASSWORD_ERROR
            = "/profile?command=go-to-profile-email-change&changeResult=illegalPassWordError";
    public static final String SUBPROFILE_GO_TO_EMAIL_CHANGE_SUCCESSFUL_RESULT
            = "/profile?command=go-to-profile-email-change&changeResult=success";

    public static final String SUBPROFILE_GO_TO_CHANGE_PASSWORD_RESULT_TECH_ERROR
            = "/profile?command=go-to-profile-change-password&changeResult=techError";
    public static final String SUBPROFILE_GO_TO_CHANGE_PASSWORD_RESULT_VALID_ERROR
            = "/profile?command=go-to-profile-change-password&changeResult=validationError";
    public static final String SUBPROFILE_GO_TO_CHANGE_PASSWORD_RESULT_DUPLICATE_ERROR
            = "/profile?command=go-to-profile-change-password&changeResult=duplicateError";
    public static final String SUBPROFILE_GO_TO_CHANGE_PASSWORD_RESULT_ILLEGAL_PASSWORD_ERROR
            = "/profile?command=go-to-profile-change-password&changeResult=illegalPassWordError";
    public static final String SUBPROFILE_GO_TO_CHANGE_PASSWORD_SUCCESSFUL_RESULT
            = "/profile?command=go-to-profile-change-password&changeResult=success";


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
    public static final String SUBPROFILE_CHANGE_EMAIL_JSP = "/WEB-INF/jsp/subprofile/changeEmail.jsp";
    public static final String SUBPROFILE_CHANGE_PASSWORD_JSP = "/WEB-INF/jsp/subprofile/changePassword.jsp";
    public static final String SUBPROFILE_CHANGE_PATIENT_INFO_JSP = "/WEB-INF/jsp/subprofile/changePatientInfo.jsp";
    public static final String SUBPROFILE_MEDICAL_HISTORY_PERMISSION_jsp = "/WEB-INF/jsp/subprofile/medicalHistoryPermission.jsp";
    public static final String SUBPROFILE_CHANGE_STAFF_INFO_JSP = "/WEB-INF/jsp/subprofile/changeStaffInfo.jsp";
    public static final String SUBPROFILE_CHANGE_STAFF_PHOTO_JSP = "/WEB-INF/jsp/subprofile/changeStaffPhoto.jsp";
    public static final String SUBPROFILE_EXTREMELY_HAZARDOUS_DISEASES_JSP = "/WEB-INF/jsp/subprofile/extremelyHazardousDiseases.jsp";
    public static final String SUBPROFILE_STAFF_HISTORY_JSP = "/WEB-INF/jsp/subprofile/staffHistory.jsp";
    public static final String SUBPROFILE_STAFF_INFO_JSP = "/WEB-INF/jsp/subprofile/staffInfo.jsp";


    private CommandPath() {
    }
}

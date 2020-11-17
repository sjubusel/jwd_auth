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

    public static final String SUBPROFILE_GO_TO_MEDICAL_HISTORY_PERMISSION_DEL_RESULT_TECH_ERROR
            = "/profile?command=go-to-profile-medical-history-permission&deleteResult=techError";
    public static final String SUBPROFILE_GO_TO_MEDICAL_HISTORY_PERMISSION_DEL_RESULT_VALID_ERROR
            = "/profile?command=go-to-profile-medical-history-permission&deleteResult=validationError";
    public static final String SUBPROFILE_GO_TO_MEDICAL_HISTORY_PERMISSION_DEL_SUCCESSFUL_RESULT
            = "/profile?command=go-to-profile-medical-history-permission&deleteResult=success";
    public static final String SUBPROFILE_GO_TO_MEDICAL_HISTORY_PERMISSION_ADD_RESULT_TECH_ERROR
            = "/profile?command=go-to-profile-medical-history-permission&addResult=techError;";
    public static final String SUBPROFILE_GO_TO_MEDICAL_HISTORY_PERMISSION_ADD_RESULT_VALID_ERROR
            = "/profile?command=go-to-profile-medical-history-permission&addResult=validationError";
    public static final String SUBPROFILE_GO_TO_MEDICAL_HISTORY_PERMISSION_ADD_SUCCESSFUL_RESULT
            = "/profile?command=go-to-profile-medical-history-permission&addResult=success";

    public static final String SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_FOOD_RESULT_TECH_ERROR
            = "/profile?command=go-to-profile-allergic-reactions&addFoodResult=techError";
    public static final String SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_FOOD_RESULT_VALID_ERROR
            = "/profile?command=go-to-profile-allergic-reactions&addFoodResult=validationError";
    public static final String SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_FOOD_SUCCESSFUL_RESULT
            = "/profile?command=go-to-profile-allergic-reactions&addFoodResult=success";

    public static final String SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_MEDICINE_RESULT_TECH_ERROR
            = "/profile?command=go-to-profile-allergic-reactions&addMedicineResult=techError";
    public static final String SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_MEDICINE_RESULT_VALID_ERROR
            = "/profile?command=go-to-profile-allergic-reactions&addMedicineResult=validationError";
    public static final String SUBPROFILE_GO_TO_ALLERGIC_REACTIONS_ADD_MEDICINE_SUCCESSFUL_RESULT
            = "/profile?command=go-to-profile-allergic-reactions&addMedicineResult=success";
    public static final String SUBPROFILE_GO_TO_EXTREMELY_HAZARDOUS_DISEASES_ADD_RESULT_TECH_ERROR
            = "/profile?command=go-to-profile-extremely-hazardous-diseases&addResult=techError";
    public static final String SUBPROFILE_GO_TO_EXTREMELY_HAZARDOUS_DISEASES_ADD_RESULT_VALID_ERROR
            = "/profile?command=go-to-profile-extremely-hazardous-diseases&addResult=validationError";
    public static final String SUBPROFILE_GO_TO_EXTREMELY_HAZARDOUS_DISEASES_ADD_SUCCESSFUL_RESULT
            = "/profile?command=go-to-profile-extremely-hazardous-diseases&addResult=success";

    public static final String SUBSTAFF_GO_TO_REGISTER_VISIT_ADD_RESULT_TECHERROR
            = "/profile?command=go-to-staff-register-visit&addResult=techError";
    public static final String SUBSTAFF_GO_TO_REGISTER_VISIT_ADD_RESULT_VAL_ERROR
            = "/profile?command=go-to-staff-register-visit&addResult=validationError";
    public static final String SUBSTAFF_GO_TO_REGISTER_VISIT_ADD_RESULT_SUCCESS
            = "/profile?command=go-to-staff-register-visit&addResult=success";

    public static final String SUBSTAFF_GO_TO_STAFF_NEW_VISITS_ACCEPT_RESULT_TECH_ERROR
            = "/profile?command=go-to-staff-new-visits&acceptResult=techError";
    public static final String SUBSTAFF_GO_TO_STAFF_NEW_VISITS_ACCEPT_RESULT_VAL_ERROR
            = "/profile?command=go-to-staff-new-visits&acceptResult=validationError";
    public static final String SUBSTAFF_GO_TO_STAFF_NEW_VISITS_ACCEPT_RESULT_SUCCESS
            = "/profile?command=go-to-staff-new-visits&acceptResult=success&hiddenVisitId=";

    public static final String SUBSTAFF_CONTROLLED_VISIT_CHANGE_RESULT_TECH_ERROR
            = "/profile?command=go-to-doctor-view-controlled-visit&changeResult=techError&hiddenVisitId=";
    public static final String SUBSTAFF_CONTROLLED_VISIT_CHANGE_RESULT_VAL_ERROR
            = "/profile?command=go-to-doctor-view-controlled-visit&changeResult=validationError&hiddenVisitId=";
    public static final String SUBSTAFF_CONTROLLED_VISIT_CHANGE_RESULT_SUCCESS
            = "/profile?command=go-to-doctor-view-controlled-visit&changeResult=success&hiddenVisitId=";

    public static final String
            SUBSTAFF_GO_TO_ESTABLISH_DIAGNOSIS_CHANGE_RESULT_TECHERROR
            = "/profile?command=go-to-establish-diagnosis&changeResult=" +
            "techError&hiddenVisitId=";
    public static final String
            SUBSTAFF_GO_TO_ESTABLISH_DIAGNOSIS_CHANGE_RESULT_VAL_ERROR
            = "/profile?command=go-to-establish-diagnosis&changeResult=" +
            "validationError&hiddenVisitId=";
    public static final String
            SUBSTAFF_GO_TO_ESTABLISH_DIAGNOSIS_CHANGE_RESULT_SUCCESS
            = "/profile?command=go-to-establish-diagnosis&changeResult=" +
            "success&hiddenVisitId=";

    public static final String
            SUBSTAFF_GO_TO_ESTABLISH_MED_PRESCRIPTION_CHANGE_RESULT_TECHERROR
            = "/profile?command=go-to-establish-med-prescription&changeResult" +
            "=techError&hiddenVisitId=";
    public static final String
            SUBSTAFF_GO_TO_ESTABLISH_MED_PRESCRIPTION_CHANGE_RESULT_VAL_ERROR
            = "/profile?command=go-to-establish-med-prescription&changeResult" +
            "=validationError&hiddenVisitId=";
    public static final String
            SUBSTAFF_GO_TO_ESTABLISH_MED_PRESCRIPTION_CHANGE_RESULT_SUCCESS
            = "/profile?command=go-to-establish-med-prescription&changeResult" +
            "=success&hiddenVisitId=";
    public static final String
            SUBSTAFF_GO_TO_ESTABLISH_MED_PRESCRIPTION_CHANGE_RESULT_ALLERGY
            = "/profile?command=go-to-establish-med-prescription&changeResult" +
            "=allergy&hiddenVisitId=";

    public static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    public static final String PROFILE_JSP = "/WEB-INF/jsp/profile.jsp";
    public static final String REGISTER_JSP = "/WEB-INF/jsp/register.jsp";
    public static final String MAIN_JSP = "/WEB-INF/jsp/main.jsp";
    public static final String ABOUT_US_JSP = "/WEB-INF/jsp/aboutUs.jsp";
    public static final String CONTACTS_JSP = "/WEB-INF/jsp/contacts.jsp";
    public static final String NEWS_JSP = "/WEB-INF/jsp/news.jsp";
    public static final String PATIENTS_JSP = "/WEB-INF/jsp/patients.jsp";
    public static final String STAFF_JSP = "/WEB-INF/jsp/staff.jsp";

    public static final String SUBPROFILE_ALLERGIC_REACTIONS_JSP
            = "/WEB-INF/jsp/subprofile/allergicReactions.jsp";
    public static final String SUBPROFILE_CHANGE_EMAIL_JSP
            = "/WEB-INF/jsp/subprofile/changeEmail.jsp";
    public static final String SUBPROFILE_CHANGE_PASSWORD_JSP
            = "/WEB-INF/jsp/subprofile/changePassword.jsp";
    public static final String SUBPROFILE_CHANGE_PATIENT_INFO_JSP
            = "/WEB-INF/jsp/subprofile/changePatientInfo.jsp";
    public static final String SUBPROFILE_MEDICAL_HISTORY_PERMISSION_jsp
            = "/WEB-INF/jsp/subprofile/medicalHistoryPermission.jsp";
    public static final String SUBPROFILE_CHANGE_STAFF_INFO_JSP
            = "/WEB-INF/jsp/subprofile/changeStaffInfo.jsp";
    public static final String SUBPROFILE_CHANGE_STAFF_PHOTO_JSP
            = "/WEB-INF/jsp/subprofile/changeStaffPhoto.jsp";
    public static final String SUBPROFILE_EXTREMELY_HAZARDOUS_DISEASES_JSP
            = "/WEB-INF/jsp/subprofile/extremelyHazardousDiseases.jsp";
    public static final String SUBPROFILE_STAFF_HISTORY_JSP
            = "/WEB-INF/jsp/subprofile/staffHistory.jsp";
    public static final String SUBPROFILE_STAFF_INFO_JSP
            = "/WEB-INF/jsp/subprofile/staffInfo.jsp";

    public static final String SUBSTAFF_REGISTER_VISIT_JSP
            = "/WEB-INF/jsp/registrar/registerVisit.jsp";
    public static final String SUBSTAFF_NEW_VISITS_JSP
            = "/WEB-INF/jsp/admission_doctor/newVisits.jsp";
    public static final String SUBSTAFF_VISIT_ON_CONTROL_JSP
            = "/WEB-INF/jsp/admission_doctor/visitOnControl.jsp";
    public static final String SUBSTAFF_VISIT_DETAIL_JSP
            = "/WEB-INF/jsp/admission_doctor/visitDetail.jsp";
    public static final String SUBSTAFF_ESTABLISH_DIAGNOSIS_JSP
            = "/WEB-INF/jsp/admission_doctor/establishDiagnosis.jsp";
    public static final String SUBSTAFF_ESTABLISH_MED_PRESCR_JSP
            = "/WEB-INF/jsp/admission_doctor/establishMedicinePrescription.jsp";
    public static final String SUBSTAFF_ESTABLISH_PRESCR_JSP
            = "/WEB-INF/jsp/admission_doctor/establishPrescription.jsp";

    private CommandPath() {
    }
}

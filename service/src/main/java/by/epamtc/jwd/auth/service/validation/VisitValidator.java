package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;

public class VisitValidator {


    public boolean isAdmissionDepartmentVisit(AdmissionDepartmentVisit hospVisit) {
        return hospVisit.getPatientShortInfo().matches(RegistrationInfoPattern.DIGITS);
    }

    public boolean isAuthUserHasRights(AuthUser user) {
        return user.getRole().getRoleId() >= AppConstant.STAFF_WHICH_CAN_TREAT;
    }

    public boolean isVisitIdCorrect(String visitId) {
        return visitId.matches(RegistrationInfoPattern.DIGITS);
    }
}

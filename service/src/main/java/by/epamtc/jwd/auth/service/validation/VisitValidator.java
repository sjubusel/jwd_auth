package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;

public class VisitValidator {


    public boolean isAdmissionDepartmentVisit(AdmissionDepartmentVisit hospVisit) {
        return hospVisit.getPatientShortInfo().matches(RegistrationInfoPattern.DIGITS);
    }
}

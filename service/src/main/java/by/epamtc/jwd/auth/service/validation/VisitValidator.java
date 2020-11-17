package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;

public class VisitValidator {


    public boolean isAdmissionDepartmentVisit(AdmissionDepartmentVisit hospVisit) {
        return hospVisit.getPatientShortInfo().matches(RegistrationInfoPattern.DIGITS);
    }

    public boolean isAuthUserHasRights(AuthUser user) {
        return user.getRole().getRoleId() >= AppConstant.STAFF_WHICH_CAN_TREAT;
    }

    public boolean isStringIdCorrect(String visitId) {
        return visitId.matches(RegistrationInfoPattern.DIGITS);
    }

    public boolean isPatientIdCorrect(int patientId) {
        return patientId > 0;
    }

    public boolean isDiagnosisCorrect(Diagnosis diagnosis) {
        return diagnosis.getDiseaseInfo().matches(RegistrationInfoPattern.DIGITS);
    }

    public boolean isMedicinePrescriptionValid(MedicinePrescription prescription) {
        if (prescription.getOriginDocumentId() < 1) {
            return false;
        }
        if (prescription.getMedicineId() < 1) {
            return false;
        }
        if (prescription.getTargetApplicationDateTime() == null) {
            return false;
        }
        if (prescription.getDosageQuantity() <= 0) {
            return false;
        }
        return (prescription.getDosageMeasureUnit() != null);
    }

    public boolean isPrescriptionValid(Prescription prescription) {
        if (prescription.getOriginDocumentId() <= 0) {
            return false;
        }
        String prescriptionDescription = prescription.getPrescriptionDescription();
        //noinspection RedundantIfStatement
        if (prescriptionDescription == null
                || prescriptionDescription.matches(AppConstant.EMPTY)) {
            return false;
        }
        return true;
    }
}

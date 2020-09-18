package by.epamtc.jwd.auth.service.validation;

import by.epamtc.jwd.auth.model.report.HospitalDepartmentReport;

public class HospitalReportValidator {
    public boolean isReportPartValid(HospitalDepartmentReport part) {
        return (part.getDeptName() != null) &&
                (part.getVacantPlacesNumber() < part.getTotalPlacesNumber());
    }
}

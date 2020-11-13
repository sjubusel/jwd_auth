package by.epamtc.jwd.auth.model.med_info;

public enum DiagnosisOrigin {
    ADMISSION_DEPARTMENT ("приёмное"),
    INNER_HOSPITAL_DEPARTMENT("стационар");

    private String description;

    DiagnosisOrigin(String description) {
        this.description = description;
    }

    DiagnosisOrigin() {
    }

    public String getDescription() {
        return description;
    }
}

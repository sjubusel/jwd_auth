package by.epamtc.jwd.auth.model.med_info;

public enum DepartmentOrigin {
    ADMISSION_DEPARTMENT ("приёмное"),
    INNER_HOSPITAL_DEPARTMENT("стационар");

    private String description;

    DepartmentOrigin(String description) {
        this.description = description;
    }

    DepartmentOrigin() {
    }

    public String getDescription() {
        return description;
    }
}

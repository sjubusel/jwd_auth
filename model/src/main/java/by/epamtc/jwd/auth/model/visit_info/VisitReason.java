package by.epamtc.jwd.auth.model.visit_info;

public enum  VisitReason {
    EMERGENCY("Скорая помощь"), INDEPENDENTLY("Самостоятельное обращение"),
    PRESCRIPTION("Предписание учреждения здравоохранения");


    private String description;

    VisitReason(String description) {
        this.description = description;
    }

    VisitReason() {
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "VisitReason{" +
                "description='" + description + '\'' +
                '}';
    }
}

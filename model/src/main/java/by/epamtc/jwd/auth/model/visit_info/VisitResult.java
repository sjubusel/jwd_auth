package by.epamtc.jwd.auth.model.visit_info;

public enum  VisitResult {
    HOSPITALIZATION("Госпитализация"), REFUSAL("Отказ в госпитализации"),
    UNAUTHORIZED_ESCAPE("Самовольное покидание больницы");

    private String description;

    VisitResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "VisitResult{" +
                "description='" + description + '\'' +
                '}';
    }
}

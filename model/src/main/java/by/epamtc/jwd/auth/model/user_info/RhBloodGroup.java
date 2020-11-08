package by.epamtc.jwd.auth.model.user_info;

public enum RhBloodGroup {
    POSITIVE("+", "положительный"), NEGATIVE("-", "отрицательный"),
    UNKNOWN("unknown", "неизвестно");

    private String value;
    private String description;

    RhBloodGroup(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}

package by.epamtc.jwd.auth.model.user_info;

public enum MaritalStatus {
    UNMARRIED("unmarried", "холост / незамужем"), MARRIED("married", "женат / замужем"),
    DIVORCED("divorced", "разведён / разведена"), WIDOWER("widower", "вдовец / вдова");

    private String value;
    private String description;

    MaritalStatus(String value, String description) {
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

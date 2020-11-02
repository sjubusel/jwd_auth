package by.epamtc.jwd.auth.model.user_info;

public enum MaritalStatus {
    UNMARRIED("холост / незамужем"), MARRIED("женат / замужем"),
    DIVORCED("разведён / разведена"), WIDOWER("вдовец / вдова");

    private String description;

    MaritalStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

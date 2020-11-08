package by.epamtc.jwd.auth.model.user_info;

public enum BloodType {
    FIRST("I"), SECOND("II"), THIRD("III"), FORTH("IV"),
    UNKNOWN("unknown");

    private String value;

    BloodType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

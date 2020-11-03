package by.epamtc.jwd.auth.model.user_info;

public enum DisabilityDegree {
    ZERO(0, "инвалидность отсутствует"),
    FIRST(1, "первая группа инвалидности"),
    SECOND(2, "вторая группа инвалидности"),
    THIRD(3, "третья группа инвалидности");

    private int value;
    private String description;

    DisabilityDegree(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}

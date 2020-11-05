package by.epamtc.jwd.auth.model.user_info;

public enum BloodType {
    FIRST(1, "I"), SECOND(2, "II"), THIRD(3, "III"), FORTH(4, "IV");

    private int number;
    private String value;

    BloodType(int number, String value) {
        this.number = number;
        this.value = value;
    }


    public int getNumber() {
        return number;
    }

    public String getValue() {
        return value;
    }
}

package by.epamtc.jwd.auth.model.user_info;

public enum Gender {
    MALE("male", "мужской"), FEMALE("female", "женский"),
    OTHER("other", "другой");

    Gender(String genderName, String genderValue) {
        this.genderName = genderName;
        this.genderValue = genderValue;
    }

    private String genderName;
    private String genderValue;

    public String getGenderName() {
        return genderName;
    }

    public String getGenderValue() {
        return genderValue;
    }
}

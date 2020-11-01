package by.epamtc.jwd.auth.model.user_info;

public enum Gender {
    MALE("male"), FEMALE("female"), OTHER("other");

    Gender(String genderName) {
        this.genderName = genderName;
    }

    private String genderName;

    public String getGenderName() {
        return genderName;
    }
}

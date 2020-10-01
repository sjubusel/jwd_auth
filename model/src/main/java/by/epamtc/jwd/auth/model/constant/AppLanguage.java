package by.epamtc.jwd.auth.model.constant;

public enum AppLanguage {
    RUSSIAN("russian", "ru_RU"), ENGLISH("english", "en_US");

    private String languageName;
    private String languageCode;

    AppLanguage(String languageName, String languageCode) {
        this.languageName = languageName;
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}

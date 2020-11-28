package by.epamtc.jwd.auth.web.util;

import by.epamtc.jwd.auth.model.constant.AppLanguage;
import by.epamtc.jwd.auth.service.ServiceFactory;

import java.util.HashMap;

public final class LanguageProvider {
    private static volatile LanguageProvider instance;

    private final HashMap<String, String> repository = new HashMap<>();

    private LanguageProvider() {
        repository.put(AppLanguage.RUSSIAN.getLanguageName(),
                AppLanguage.RUSSIAN.getLanguageCode());
        repository.put(AppLanguage.ENGLISH.getLanguageName(),
                AppLanguage.ENGLISH.getLanguageCode());
    }

    public static LanguageProvider getInstance() {
        LanguageProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new LanguageProvider();
                }
            }
        }
        return localInstance;
    }

    public String provideLanguageCode(String language) {
        String languageCode = repository.get(language);
        return (languageCode != null)
               ? languageCode
               : repository.get(AppLanguage.RUSSIAN.getLanguageName());
    }
}

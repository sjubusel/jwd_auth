package by.epamtc.jwd.auth.web.util;

import by.epamtc.jwd.auth.service.ServiceFactory;

import java.util.HashMap;

public class LanguageProvider {
    private static volatile LanguageProvider instance;

    private final HashMap<String, String> repository = new HashMap<>();

    private LanguageProvider() {
        repository.put("русский", "ru_RU");
        repository.put("english", "en_US");
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
        return languageCode != null ? languageCode : repository.get("русский");
    }
}

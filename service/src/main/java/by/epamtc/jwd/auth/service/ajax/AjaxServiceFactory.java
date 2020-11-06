package by.epamtc.jwd.auth.service.ajax;

import by.epamtc.jwd.auth.service.ajax.impl.DefaultAjaxFetchService;

public class AjaxServiceFactory {
    private static volatile AjaxServiceFactory instance;
    private final AjaxFetchService ajaxFetchService = new DefaultAjaxFetchService();

    private AjaxServiceFactory() {
    }

    public static AjaxServiceFactory getInstance() {
        AjaxServiceFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (AjaxServiceFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AjaxServiceFactory();
                }
            }
        }
        return localInstance;
    }

    public AjaxFetchService getAjaxFetchService() {
        return ajaxFetchService;
    }
}

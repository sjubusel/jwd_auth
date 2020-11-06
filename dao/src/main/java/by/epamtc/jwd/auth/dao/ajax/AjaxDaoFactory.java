package by.epamtc.jwd.auth.dao.ajax;

import by.epamtc.jwd.auth.dao.ajax.impl.DefaultAjaxFetchDao;

public class AjaxDaoFactory {

    private static volatile AjaxDaoFactory instance;

    private final AjaxFetchDao ajaxFetchDao = new DefaultAjaxFetchDao();

    private AjaxDaoFactory() {
    }

    public static AjaxDaoFactory getInstance() {
        AjaxDaoFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (AjaxDaoFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AjaxDaoFactory();
                }
            }
        }
        return localInstance;
    }
}

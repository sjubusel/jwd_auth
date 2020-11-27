package by.epamtc.jwd.auth.dao.ajax_dao;

import by.epamtc.jwd.auth.dao.ajax_dao.impl.DefaultAjaxFetchDao;

/**
 * a class which provides necessary AjaxDao interfaces on the service layer and by
 * its nature hides exact implementations of dao interfaces from a client on
 * the service layer. It is of a Singleton pattern.
 */
public class AjaxDaoFactory {
    /**
     * a unique and only instance of this class
     */
    private static volatile AjaxDaoFactory instance;
    /**
     * a field which represent an implementation of AjaxFetchDao used
     * at the present time
     */
    private final AjaxFetchDao ajaxFetchDao = new DefaultAjaxFetchDao();

    /**
     * a private constructor of a Singleton pattern
     */
    private AjaxDaoFactory() {
    }

    /**
     * a method which initialize and provide thread safe instance of a
     * current type
     *
     * @return an unique and only instance of a current class
     */
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

    /**
     * a standard getter method for {@link AjaxDaoFactory#ajaxFetchDao}
     *
     * @return a field {@link AjaxDaoFactory#ajaxFetchDao}
     */
    public AjaxFetchDao getAjaxFetchDao() {
        return ajaxFetchDao;
    }
}

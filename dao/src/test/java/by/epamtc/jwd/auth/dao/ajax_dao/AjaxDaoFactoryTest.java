package by.epamtc.jwd.auth.dao.ajax_dao;

import by.epamtc.jwd.auth.dao.ajax_dao.impl.DefaultAjaxFetchDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AjaxDaoFactoryTest {
    private AjaxDaoFactory ajaxDaoFactory = AjaxDaoFactory.getInstance();

    @DisplayName("test whether Singleton is correct")
    @Test
    void testGetInstance() {
        AjaxDaoFactory expected = ajaxDaoFactory;
        AjaxDaoFactory actual = AjaxDaoFactory.getInstance();
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("test whether correct implementation of AjaxFetchDao " +
            "interface is chosen")
    @Test
    void testGetAjaxFetchDao() {
        AjaxFetchDao ajaxFetchDao = ajaxDaoFactory.getAjaxFetchDao();
        Assertions.assertEquals(DefaultAjaxFetchDao.class, ajaxFetchDao.getClass());
    }
}
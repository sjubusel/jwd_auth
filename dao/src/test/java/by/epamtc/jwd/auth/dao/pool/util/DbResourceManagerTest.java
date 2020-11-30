package by.epamtc.jwd.auth.dao.pool.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DbResourceManagerTest {
    public static final String TEST_PROPERTY = "jdbc:mysql://localhost:3308/";

    private DbResourceManager manager = DbResourceManager.getInstance();

    @DisplayName("test whether Singleton is correct")
    @Test
    void testGetInstance() {
        DbResourceManager expected = manager;
        DbResourceManager actual = DbResourceManager.getInstance();
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("test whether DbResourceManager fetches from a src file " +
            "a correct properties")
    @Test
    void testGetValue() {
        String actual = manager.getValue(DbParameter.DB_URL);
        Assertions.assertEquals(TEST_PROPERTY, actual);
    }
}
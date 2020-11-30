package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.AuthUserDao;
import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.AuthenticationInfo;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.model.auth_info.Role;
import by.epamtc.jwd.auth.model.user_info.Gender;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

class DefaultAuthUserDaoTest {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("db");
    private static final String SQL_SCRIPT_CREATION = bundle.getString("scriptPath");
    private static final String SQL_SCRIPT_FILLING = bundle.getString("scriptPath2");
    private static final String DRIVER_CLASS = bundle.getString("driver");
    private static final String DATABASE_URL = bundle.getString("url");
    private static final String DATABASE_USER = bundle.getString("login");
    private static final String DATABASE_PASSWORD = bundle.getString("password");

    private static Connection connection;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private AuthUserDao trgDao = daoFactory.getAuthUserDao();

    @BeforeAll
    static void beforeAll() throws ClassNotFoundException, SQLException,
            IOException {
        Class.forName(DRIVER_CLASS);
        connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER,
                DATABASE_PASSWORD);
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        scriptRunner.setLogWriter(null);
        FileReader fileReader = new FileReader(SQL_SCRIPT_CREATION);
        BufferedReader reader = new BufferedReader(fileReader);
        scriptRunner.runScript(reader);
        fileReader.close();
        reader.close();
        FileReader fReader = new FileReader(SQL_SCRIPT_FILLING);
        BufferedReader bufferedReader = new BufferedReader(fReader);
        scriptRunner.runScript(bufferedReader);
        fReader.close();
        bufferedReader.close();
        connection.setAutoCommit(true);
    }

    @AfterAll
    static void afterAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DROP DATABASE hospital;");
        statement.execute();
        closeStatement(statement);
        closeConnection(connection);
    }

    @DisplayName("test registration")
    @Test
    void testSaveAuthUser() throws DaoException {
        String login = "sjubusel";
        String password = "sjubusel";
        String email = "sjubusel@tut.by";
        String phoneNumber = "+375 (29) 111-11-11";
        String firstName = "Сергей";
        String lastName = "Бусел";
        LocalDate birthDay = LocalDate.now();

        AuthUser expected = new AuthUser(13, firstName, null, lastName,
                Role.USER, 11, 0);

        AuthUser actual = trgDao.saveAuthUser(new RegistrationInfo(login,
                password, email, phoneNumber, firstName, null,
                lastName, birthDay, Gender.MALE));

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("test authentication")
    @Test
    void testReceiveAuthUserIfCorrect() throws DaoException {
        AuthUser expected = new AuthUser(3, "Иван", "Степанович", "Теропевтов",
                Role.ADMISSION_DOCTOR, 1, 1);

        AuthUser actual = trgDao.receiveAuthUserIfCorrect(
                new AuthenticationInfo("adphysician", "mainmain"));

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("test a positive result of presence of login in the system")
    @Test
    void testContainsLoginIfTrue() throws DaoException {
        String login = "adphysician";
        boolean actual = trgDao.containsLogin(login);
        Assertions.assertTrue(actual);
    }

    @DisplayName("test a negative result of presence of login in the system")
    @Test
    void testContainsLoginIfFalse() throws DaoException {
        String login = "qwe@tut.by";
        boolean actual = trgDao.containsLogin(login);
        Assertions.assertFalse(actual);
    }

    @DisplayName("test a positive result of presence of email in the system")
    @Test
    void testContainsEmailIfTrue() throws DaoException {
        String email = "adphysician@tut.by";
        boolean actual = trgDao.containsEmail(email);
        Assertions.assertTrue(actual);
    }

    @DisplayName("test a negative result of presence of email in the system")
    @Test
    void testContainsEmailIfFalse() throws DaoException {
        String email = "qwe@tut.by";
        boolean actual = trgDao.containsEmail(email);
        Assertions.assertFalse(actual);
    }

    private void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null && !resultSet.isClosed()) {
            resultSet.close();
        }
    }

    private static void closeStatement(PreparedStatement statement) throws SQLException {
        if (statement != null && !statement.isClosed()) {
            statement.close();
        }
    }

    private static void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
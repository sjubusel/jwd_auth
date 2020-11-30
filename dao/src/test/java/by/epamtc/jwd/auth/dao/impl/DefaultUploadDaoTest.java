package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.UploadDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.fail;

class DefaultUploadDaoTest {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("db");
    private static final String SQL_SCRIPT_CREATION = bundle.getString("scriptPath");
    private static final String SQL_SCRIPT_FILLING = bundle.getString("scriptPath2");
    private static final String DRIVER_CLASS = bundle.getString("driver");
    private static final String DATABASE_URL = bundle.getString("url");
    private static final String DATABASE_USER = bundle.getString("login");
    private static final String DATABASE_PASSWORD = bundle.getString("password");

    private static Connection connection;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UploadDao trgDao = daoFactory.getUploadDao();

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

    @Test
    void testUpdatePatientPhoto() throws SQLException, DaoException {
        String targetFileName = "/upload/user_photo/photo20201130.png";
        AuthUser user = new AuthUser();
        user.setUserId(10);

        PreparedStatement statementBefore = connection.prepareStatement(
                "SELECT p.photo_path FROM hospital.persons p " +
                        "WHERE p.person_id = ?"
        );
        statementBefore.setInt(1, user.getUserId());
        ResultSet resultSetBefore = statementBefore.executeQuery();
        if (resultSetBefore.next()) {
            if (resultSetBefore.getString(1) != null) {
                fail();
            }
        }
        closeResultSet(resultSetBefore);
        closeStatement(statementBefore);

        boolean isUpdated = trgDao.updatePatientPhoto(targetFileName, user);

        if (!isUpdated) {
            fail();
        }

        PreparedStatement statementAfter = connection.prepareStatement(
                "SELECT p.photo_path FROM hospital.persons p " +
                        "WHERE p.person_id = ?"
        );
        statementAfter.setInt(1, user.getUserId());
        ResultSet resultSetAfter = statementAfter.executeQuery();
        if (!resultSetAfter.next()) {
            fail();

        }
        String actual = resultSetAfter.getString(1);
        closeResultSet(resultSetAfter);
        closeStatement(statementAfter);

        Assertions.assertEquals(targetFileName, actual);
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
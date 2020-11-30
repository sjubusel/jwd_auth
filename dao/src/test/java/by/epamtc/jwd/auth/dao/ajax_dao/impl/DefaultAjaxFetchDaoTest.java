package by.epamtc.jwd.auth.dao.ajax_dao.impl;

import by.epamtc.jwd.auth.dao.ajax_dao.AjaxDaoFactory;
import by.epamtc.jwd.auth.dao.ajax_dao.AjaxFetchDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxArea;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxCountry;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxRegion;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

class DefaultAjaxFetchDaoTest {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("db");
    private static final String SQL_SCRIPT_CREATION = bundle.getString("scriptPath");
    private static final String SQL_SCRIPT_FILLING = bundle.getString("scriptPath2");
    private static final String DRIVER_CLASS = bundle.getString("driver");
    private static final String DATABASE_URL = bundle.getString("url");
    private static final String DATABASE_USER = bundle.getString("login");
    private static final String DATABASE_PASSWORD = bundle.getString("password");

    private static Connection connection;
    private AjaxDaoFactory daoFactory = AjaxDaoFactory.getInstance();
    private AjaxFetchDao ajaxFetchDao = daoFactory.getAjaxFetchDao();

    @BeforeAll
    static void beforeAll() throws IOException, ClassNotFoundException,
            SQLException {
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

    @DisplayName("test whether countries are correctly fetched")
    @Test
    void testFetchCountries() throws DaoException {
        List<AjaxCountry> expected = new ArrayList<>();
        expected.add(new AjaxCountry(1, "АФГАНИСТАН"));

        List<AjaxCountry> actual = ajaxFetchDao.fetchCountries("АФГ");
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("test whether regions within a country are correctly fetched")
    @Test
    void fetchRegions() throws DaoException {
        List<AjaxRegion> expected = new ArrayList<>();
        expected.add(new AjaxRegion(1, "МИНСК ГОРОД", "БЕЛАРУСЬ"));
        expected.add(new AjaxRegion(2, "МИНСКАЯ ОБЛАСТЬ", "БЕЛАРУСЬ"));

        List<AjaxRegion> actual = ajaxFetchDao.fetchRegions(34, "МИН");

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("test whether area within a region are correctly fetched")
    @Test
    void fetchAreas() throws DaoException {
        List<AjaxArea> expected = new ArrayList<>();
        expected.add(new AjaxArea(3, "ГОМЕЛЬСКИЙ РАЙОН", "ГОМЕЛЬСКАЯ"));

        List<AjaxArea> actual = ajaxFetchDao.fetchAreas(3, "ГОМ");
        Assertions.assertEquals(expected, actual);
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
package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.HospitalReportDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.report.HospitalDepartmentReport;
import by.epamtc.jwd.auth.model.report.HospitalReport;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;

class DefaultHospitalReportDaoTest {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("db");
    private static final String SQL_SCRIPT_CREATION = bundle.getString("scriptPath");
    private static final String SQL_SCRIPT_FILLING = bundle.getString("scriptPath2");
    private static final String DRIVER_CLASS = bundle.getString("driver");
    private static final String DATABASE_URL = bundle.getString("url");
    private static final String DATABASE_USER = bundle.getString("login");
    private static final String DATABASE_PASSWORD = bundle.getString("password");

    private static Connection connection;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private HospitalReportDao trgDao = daoFactory.getHospitalReportDao();

    @BeforeAll
    static void beforeAll() throws ClassNotFoundException, SQLException,
            IOException {
        Class.forName(DRIVER_CLASS);
        connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER,
                DATABASE_PASSWORD);
        ScriptRunner scriptRunner = new ScriptRunner(connection);
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

    @DisplayName("test whether a stub fillability report is correct")
    @Test
    void receiveHospitalFillability() throws DaoException {
        HospitalReport expected = new HospitalReport();
        expected.addReportPart(new HospitalDepartmentReport(
                "ТЕРАПЕВТИЧЕСКОЕ ОТДЕЛЕНИЕ", 50, 100));
        expected.addReportPart(new HospitalDepartmentReport(
                "КАРДИОЛОГИЧЕСКОЕ ОТДЕЛЕНИЕ", 34, 99));
        expected.addReportPart(new HospitalDepartmentReport(
                "ХИРУРГИЧЕСКОЕ ОТДЕЛЕНИЕ", 86, 100));
        expected.addReportPart(new HospitalDepartmentReport(
                "НЕВРОЛОГИЧЕСКОЕ ОТДЕЛЕНИЕ", 78, 100));
        expected.addReportPart(new HospitalDepartmentReport(
                "ПУЛЬМОНОЛОГИЧЕСКОЕ ОТДЕЛЕНИЕ", 32, 120));
        expected.addReportPart(new HospitalDepartmentReport(
                "ЭНДОКРИНОЛОГИЧЕСКОЕ ОТДЕЛЕНИЕ", 23, 80));

        HospitalReport actual = trgDao.receiveHospitalFillability();

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
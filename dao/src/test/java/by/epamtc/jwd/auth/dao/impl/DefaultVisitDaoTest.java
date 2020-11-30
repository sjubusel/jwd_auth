package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.VisitDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.user_info.TransportationStatus;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.VisitReason;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.fail;

class DefaultVisitDaoTest {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("db");
    private static final String SQL_SCRIPT_CREATION = bundle.getString("scriptPath");
    private static final String SQL_SCRIPT_FILLING = bundle.getString("scriptPath2");
    private static final String DRIVER_CLASS = bundle.getString("driver");
    private static final String DATABASE_URL = bundle.getString("url");
    private static final String DATABASE_USER = bundle.getString("login");
    private static final String DATABASE_PASSWORD = bundle.getString("password");

    private static Connection connection;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private VisitDao trgDao = daoFactory.getVisitDao();

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

    @DisplayName("test registration of a visit to the admitting department")
    @Test
    @Disabled
    void testRegisterVisit() throws DaoException, SQLException {
        AdmissionDepartmentVisit expectedVisit = new AdmissionDepartmentVisit();
        expectedVisit.setPatientShortInfo(String.valueOf(10));
        expectedVisit.setVisitReason(VisitReason.INDEPENDENTLY);
        expectedVisit.setPatientVisitDescriptionInfo("test description");
        expectedVisit.setTransportationStatus(TransportationStatus.WALKING);

        boolean isRegistered = trgDao.registerVisit(expectedVisit);

        if (!isRegistered) {
            fail();
        }

        AdmissionDepartmentVisit actualVisit = new AdmissionDepartmentVisit();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT v2ad.person_id, v2ad.visit_reason," +
                        " v2ad.visit_reason_description," +
                        " v2ad.transportation_status " +
                        "FROM hospital.visits_to_admission_department v2ad " +
                        "WHERE v2ad.visit_id " +
                        "IN (SELECT MAX(visit_id) " +
                        "FROM hospital.visits_to_admission_department)"
        );
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            fail();
        }
        actualVisit.setPatientShortInfo(String.valueOf(resultSet.getInt(1)));
        actualVisit.setVisitReason(VisitReason.valueOf(resultSet.getString(2)));
        actualVisit.setPatientVisitDescriptionInfo(resultSet.getString(3));
        actualVisit.setTransportationStatus(TransportationStatus.valueOf(
                resultSet.getString(4)
        ));
        closeResultSet(resultSet);
        closeStatement(statement);

        Assertions.assertEquals(expectedVisit, actualVisit);
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
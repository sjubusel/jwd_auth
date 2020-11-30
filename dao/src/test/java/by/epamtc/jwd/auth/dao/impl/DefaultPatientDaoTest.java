package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.PatientDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.user_info.TransportationStatus;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.VisitReason;
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
import java.util.ResourceBundle;

class DefaultPatientDaoTest {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("db");
    private static final String SQL_SCRIPT_CREATION = bundle.getString("scriptPath");
    private static final String SQL_SCRIPT_FILLING = bundle.getString("scriptPath2");
    private static final String DRIVER_CLASS = bundle.getString("driver");
    private static final String DATABASE_URL = bundle.getString("url");
    private static final String DATABASE_USER = bundle.getString("login");
    private static final String DATABASE_PASSWORD = bundle.getString("password");

    private static Connection connection;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private PatientDao trgDao = daoFactory.getPatientDao();

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

    @DisplayName("test fetching a full visit information")
    @Test
    void fetchFullVisitIfExist() throws SQLException, DaoException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO hospital.visits_to_admission_department (" +
                        "person_id, visit_datetime, visit_reason," +
                        " visit_reason_description,\n" +
                        " responsible_doctor_id, transportation_status,\n" +
                        "  responsible_paramedical_staff_id)\n" +
                        "VALUES (10, CURRENT_TIMESTAMP, 'independently', " +
                        "'БОЛЬ В ЖИВОТЕ в 13:45 15.10.2020', 1, 'WALKING', 3)");
        statement.executeUpdate();
        closeStatement(statement);
        AdmissionDepartmentVisit expected = new AdmissionDepartmentVisit(3,
                null, 10, "Синяк Михаил Олегович", VisitReason.INDEPENDENTLY,
                "БОЛЬ В ЖИВОТЕ в 13:45 15.10.2020", 1,
                "Теропевтов Иван Степанович", TransportationStatus.WALKING, 3,
                "Дубина Игорь Юрьевич", null, null, 0, null, false);

        AdmissionDepartmentVisit actual = trgDao.fetchFullVisitIfExist(
                new AuthUser(0, null, null, null, null, 10, 0));
        expected.setVisitDateTime(actual.getVisitDateTime());

        Assertions.assertEquals(expected, actual);
    }

    @AfterAll
    static void afterAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DROP DATABASE hospital;");
        statement.execute();
        closeStatement(statement);
        closeConnection(connection);
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
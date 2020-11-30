package by.epamtc.jwd.auth.dao.util;

import by.epamtc.jwd.auth.model.constant.SqlStatement;
import by.epamtc.jwd.auth.model.user_info.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

class ProfileRelatedEntitiesCompilerTest {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("db");
    private static final String SQL_SCRIPT_CREATION = bundle.getString("scriptPath");
    private static final String SQL_SCRIPT_FILLING = bundle.getString("scriptPath2");
    private static final String DRIVER_CLASS = bundle.getString("driver");
    private static final String DATABASE_URL = bundle.getString("url");
    private static final String DATABASE_USER = bundle.getString("login");
    private static final String DATABASE_PASSWORD = bundle.getString("password");

    private static Connection connection;
    private ProfileRelatedEntitiesCompiler testCompiler
            = ProfileRelatedEntitiesCompiler.getInstance();

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

    @DisplayName("test generating of information about a patient " +
            "(by.epamtc.jwd.auth.model.user_info.PatientInfo)")
    @Test
    void testCompilePatientInfo() throws SQLException {
        IdentityDocument expectedIdentityDocument = new IdentityDocument(2,
                IdentificationDocumentType.PASSPORT, "MP", 2211356, "SINYAK",
                "MIKHAIL", "Республика Беларусь",
                LocalDate.of(1963, Month.NOVEMBER, 10),
                "3101163B01PB7", Gender.MALE, "РОССИЙСКАЯ ФЕДЕРАЦИЯ, " +
                "Г.САНКТ-ПЕТЕРБУРГ", LocalDate.of(2015, Month.APRIL, 1),
                LocalDate.of(2015, Month.APRIL, 30), "МВД РБ");
        Address expectedAddress = new Address(8, "220000", "БЕЛАРУСЬ",
                "ГОРОД МИНСК", "ГОРОД МИНСК", "ГОРОД МИНСК",
                "ПРОСПЕКТ ПОБЕДИТЕЛЕЙ", "85", null, null);
        PatientInfo expected = new PatientInfo(null, "Михаил", "Олегович",
                "Синяк", LocalDate.of(1963, Month.NOVEMBER, 10), Gender.MALE,
                "refusalpatient@tut.by", "+375(29) 102-01-02",
                MaritalStatus.MARRIED, expectedIdentityDocument, expectedAddress,
                "супруга Кругликова Софья Николаевна", "+375(29) 111-01-02",
                BloodType.FIRST, RhBloodGroup.NEGATIVE, DisabilityDegree.ZERO,
                TransportationStatus.WALKING, true, true);

        PreparedStatement statement = connection.prepareStatement(SqlStatement
                .SELECT_PATIENT_INFO);
        statement.setInt(1, 10);
        ResultSet resultSet = statement.executeQuery();
        PatientInfo actual = null;
        if (resultSet.next()) {
            actual = testCompiler.compilePatientInfo(resultSet, 1);
        }
        closeResultSet(resultSet);
        closeStatement(statement);

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
package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.HospitalReportDao;
import by.epamtc.jwd.auth.dao.SourceConnector;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.report.HospitalDepartmentReport;
import by.epamtc.jwd.auth.model.report.HospitalReport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultHospitalReportDao implements HospitalReportDao {

    private SourceConnector sourceConnector = DataBaseSourceConnector.getInstance();

    @Override
    public HospitalReport receiveHospitalFillability() throws DaoException {
        try (Connection connection = sourceConnector.getConnection()) {
            return receiveHospitalReportFromDB(connection);
        } catch (SQLException e) {
            throw new DaoException("An error of a connection to a datasource " +
                    "occurred", e);
        }
    }

    private HospitalReport receiveHospitalReportFromDB(Connection connection)
            throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resSet = extractSourceDataFromDB(statement);

        if (resSet == null) {
            return new HospitalReport();
        }

        return formReportFromSourceDataIfPossible(resSet);
    }

    private ResultSet extractSourceDataFromDB(Statement statement)
            throws SQLException {
        return statement.executeQuery("SELECT dep.name, " +
                "f.vacant_places_quantity, f.total_places_quantity\n" +
                "FROM hospital.stub_table_fillability f\n" +
                "JOIN hospital.hospital_departments dep " +
                "ON f.hospital_department_id = dep.hospital_department_id");
    }

    private HospitalReport formReportFromSourceDataIfPossible(ResultSet resSet)
            throws SQLException {
        HospitalReport report = new HospitalReport();
        while (resSet.next()) {
            String deptName = resSet.getString(1);
            int vacantPlacesNumber = resSet.getInt(2);
            int totalPlacesNumber = resSet.getInt(3);
            report.addReportPart(new HospitalDepartmentReport
                    (deptName, vacantPlacesNumber, totalPlacesNumber));
        }
        return report;
    }

}

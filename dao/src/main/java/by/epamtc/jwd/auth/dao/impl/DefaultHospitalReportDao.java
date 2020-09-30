package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.HospitalReportDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.report.HospitalDepartmentReport;
import by.epamtc.jwd.auth.model.report.HospitalReport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultHospitalReportDao implements HospitalReportDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public HospitalReport receiveHospitalFillability() throws DaoException {
        Connection conn = null;
        Statement stat = null;
        ResultSet rSet = null;

        try {
            conn = pool.takeConnection();
            return receiveHospitalReportFromDB(conn, stat, rSet);
        } catch (SQLException e) {
            throw new DaoException("An error of a connection to a datasource " +
                    "occurred", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool", e);
        } finally {
            pool.closeConnection(conn, stat, rSet);
        }
    }

    private HospitalReport receiveHospitalReportFromDB(Connection connection,
            Statement statement, ResultSet resultSet)
            throws SQLException {
        statement = connection.createStatement();
        resultSet = extractSourceDataFromDB(statement);

        if (resultSet == null) {
            return new HospitalReport();
        }

        return formReportFromSourceDataIfPossible(resultSet);
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

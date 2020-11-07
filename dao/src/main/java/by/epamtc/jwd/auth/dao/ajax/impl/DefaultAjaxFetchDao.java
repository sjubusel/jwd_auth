package by.epamtc.jwd.auth.dao.ajax.impl;

import by.epamtc.jwd.auth.dao.ajax.AjaxFetchDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.ajax.AjaxCountry;
import by.epamtc.jwd.auth.model.ajax.AjaxRegion;
import by.epamtc.jwd.auth.model.constant.AppConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultAjaxFetchDao implements AjaxFetchDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public List<AjaxCountry> fetchCountries(String countryPart) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxCountry> countries = new ArrayList<>();
        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement("SELECT h.country_id, h.short_country_name FROM hospital.countries h WHERE h.short_country_name LIKE CONCAT('%', ?, '%')");
            statement.setString(1, countryPart);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                countries.add(new AjaxCountry(rSet.getInt(1),
                        rSet.getString(2)));
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of countries", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching countries " +
                    "from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return countries;
    }

    @Override
    public List<AjaxRegion> fetchRegions(int countryNumber, String regionInput)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxRegion> regions = new ArrayList<>();

        try {
            conn = pool.takeConnection();
//        statement = conn.prepareStatement("SELECT h.country_id, h.short_country_name FROM hospital.countries h WHERE h.short_country_name LIKE CONCAT('%', ?, '%')");
            statement = conn.prepareStatement(
                    "SELECT r.region_id, r.region_name, rt.region_type_name, c.short_country_name\n" +
                            "FROM hospital.regions r\n" +
                            "         JOIN hospital.region_types rt ON r.region_type_id = rt.region_type_id\n" +
                            "         JOIN hospital.countries c ON r.country_id = c.country_id\n" +
                            "WHERE r.country_id = ? AND r.region_name LIKE CONCAT('%', ?, '%');"
            );
            statement.setInt(1, countryNumber);
            statement.setString(2, regionInput);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                int regionId = rSet.getInt(1);
                String regionName = rSet.getString(2) + AppConstant
                        .ONE_WHITESPACE + rSet.getString(3);
                String countryName = rSet.getString(4);
                regions.add(new AjaxRegion(regionId, regionName, countryName));
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of regions", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching regions " +
                    "from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return regions;
    }
}

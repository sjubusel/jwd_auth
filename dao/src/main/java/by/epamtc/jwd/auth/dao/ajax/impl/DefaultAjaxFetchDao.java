package by.epamtc.jwd.auth.dao.ajax.impl;

import by.epamtc.jwd.auth.dao.ajax.AjaxFetchDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.ajax.AjaxArea;
import by.epamtc.jwd.auth.model.ajax.AjaxCountry;
import by.epamtc.jwd.auth.model.ajax.AjaxRegion;
import by.epamtc.jwd.auth.model.ajax.AjaxRoad;
import by.epamtc.jwd.auth.model.ajax.AjaxSettlement;
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

    @Override
    public List<AjaxArea> fetchAreas(int regionNumber, String areaInput)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxArea> areas = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(
                    "SELECT a.area_id, a.area_name, at.area_type_name, r.region_name\n" +
                            "FROM hospital.areas a\n" +
                            "         JOIN hospital.area_types at ON a.area_type_id = at.area_type_id\n" +
                            "         JOIN hospital.regions r ON a.region_id = r.region_id\n" +
                            "WHERE a.region_id = ?\n" +
                            "  AND a.area_name LIKE CONCAT('%', ?, '%');"
            );
            statement.setInt(1, regionNumber);
            statement.setString(2, areaInput);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                int areaId = rSet.getInt(1);
                String areaName = rSet.getString(2) + AppConstant
                        .ONE_WHITESPACE + rSet.getString(3);
                String regionName = rSet.getString(4);
                areas.add(new AjaxArea(areaId, areaName, regionName));
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of areas", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching areas " +
                    "from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return areas;
    }

    @Override
    public List<AjaxSettlement> fetchSettlements(int areaNumber,
            String settlementInput) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxSettlement> settlements = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(
                    "SELECT s.settlement_id, s.settlement_name, st.settlement_type_name, a.area_name\n" +
                            "FROM hospital.settlements s\n" +
                            "         JOIN hospital.settlement_types st ON s.settlement_type_id = st.settlement_type_id\n" +
                            "         JOIN hospital.areas a ON s.area_id = a.area_id\n" +
                            "WHERE s.area_id = ?\n" +
                            "  AND s.settlement_name LIKE CONCAT('%', ?, '%');"
            );
            statement.setInt(1, areaNumber);
            statement.setString(2, settlementInput);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                int settlementId = rSet.getInt(1);
                String settlementName = rSet.getString(2) + AppConstant
                        .ONE_WHITESPACE + rSet.getString(3);
                String areaName = rSet.getString(4);
                settlements.add(new AjaxSettlement(settlementId, settlementName,
                        areaName));
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of settlements", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching settlements " +
                    "from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return settlements;
    }

    @Override
    public List<AjaxRoad> fetchRoads(int settlementNumber, String roadInput) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxRoad> roads = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(
                    "SELECT r.road_id, r.road_name, rt.road_type_name, s.settlement_name\n" +
                            "FROM hospital.roads r\n" +
                            "         JOIN hospital.road_types rt ON r.road_type_id = rt.road_type_id\n" +
                            "         JOIN hospital.settlements s ON r.settlement_id = s.settlement_id\n" +
                            "WHERE r.settlement_id = ?\n" +
                            "  AND r.road_name LIKE CONCAT('%', ?, '%');"
            );
            statement.setInt(1, settlementNumber);
            statement.setString(2, roadInput);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                int roadId = rSet.getInt(1);
                String roadName = rSet.getString(2) + AppConstant
                        .ONE_WHITESPACE + rSet.getString(3);
                String settlementName = rSet.getString(4);
                roads.add(new AjaxRoad(roadId, roadName, settlementName));
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of roads", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching roads " +
                    "from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return roads;
    }
}

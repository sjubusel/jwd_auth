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
import by.epamtc.jwd.auth.model.ajax.AjaxSqlStatement;
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
            statement = conn.prepareStatement(AjaxSqlStatement.SELECT_COUNTRIES);
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
            statement = conn.prepareStatement(AjaxSqlStatement.SELECT_REGIONS);
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
            statement = conn.prepareStatement(AjaxSqlStatement.SELECT_AREAS);
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
            statement = conn.prepareStatement(AjaxSqlStatement
                    .SELECT_SETTLEMENTS);
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
            statement = conn.prepareStatement(AjaxSqlStatement.SELECT_ROADS);
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

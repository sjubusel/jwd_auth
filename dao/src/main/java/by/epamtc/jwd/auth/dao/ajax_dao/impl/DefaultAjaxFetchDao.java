package by.epamtc.jwd.auth.dao.ajax_dao.impl;

import by.epamtc.jwd.auth.dao.ajax_dao.AjaxFetchDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxArea;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxCountry;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxDisease;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxFoodType;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxHazardousDisease;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxMedicine;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxMedicineType;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxPerson;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxRegion;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxRoad;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxSettlement;
import by.epamtc.jwd.auth.model.ajax_entity.AjaxSqlStatement;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
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

    @Override
    public List<AjaxPerson> fetchPersons(String personPart)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxPerson> persons = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(AjaxSqlStatement.SELECT_PERSONS);
            statement.setString(1, personPart);
            statement.setString(2, personPart);
            statement.setString(3, personPart);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                int personId = rSet.getInt(1);
                String firstName = rSet.getString(2);
                String middleName = rSet.getString(3);
                String lastName = rSet.getString(4);
                String personInfo = (middleName != null) ? firstName + AppConstant
                        .ONE_WHITESPACE + middleName + AppConstant.ONE_WHITESPACE
                        + lastName
                                                         : firstName + AppConstant
                        .ONE_WHITESPACE + lastName;
                persons.add(new AjaxPerson(personId, personInfo));
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of persons", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching persons " +
                    "from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return persons;
    }

    @Override
    public List<AjaxFoodType> fetchFoodTypes(String foodTypePart) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxFoodType> foodTypes = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(AjaxSqlStatement.SELECT_FOOD_TYPES);
            statement.setString(1, foodTypePart);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                int foodTypeId = rSet.getInt(1);
                String foodTypeName = rSet.getString(2);
                foodTypes.add(new AjaxFoodType(foodTypeId, foodTypeName));
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of food types", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching food types " +
                    "from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return foodTypes;
    }

    @Override
    public List<AjaxMedicineType> fetchMedicineTypes(String medicineTypePart) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxMedicineType> medicineTypes = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(AjaxSqlStatement.SELECT_MEDICINE_TYPES);
            statement.setString(1, medicineTypePart);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                int medicineTypeId = rSet.getInt(1);
                String medicineTypeName = rSet.getString(2);
                medicineTypes.add(new AjaxMedicineType(medicineTypeId,
                        medicineTypeName));
            }
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of medicine types", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching medicine types " +
                    "from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return medicineTypes;
    }

    @Override
    public List<AjaxHazardousDisease> fetchExtremelyHazardousDiseases(String
            diseasePart) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxHazardousDisease> diseases = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(AjaxSqlStatement
                    .SELECT_EXTREMELY_HAZARDOUS_DISEASES);
            statement.setString(1, diseasePart);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                int diseaseId = rSet.getInt(1);
                String diseaseName = rSet.getString(2);
                diseases.add(new AjaxHazardousDisease(diseaseId, diseaseName));
            }

        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of " +
                    "AjaxHazardousDisease", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching " +
                    "AjaxHazardousDisease from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }
        return diseases;
    }

    @Override
    public List<AjaxDisease> fetchDiseases(String diseasePart)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxDisease> diseases = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(AjaxSqlStatement
                    .SELECT_DISEASES);
            statement.setString(1, diseasePart);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                int diseaseId = rSet.getInt(1);
                String diseaseName = rSet.getString(2);
                diseases.add(new AjaxDisease(diseaseId, diseaseName));
            }

        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of " +
                    "AjaxDisease", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching " +
                    "AjaxDisease from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return diseases;
    }

    @Override
    public List<AjaxMedicine> fetchMedicines(String medicinePart)
            throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        List<AjaxMedicine> medicines = new ArrayList<>();

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(AjaxSqlStatement
                    .SELECT_MEDICINES);
            statement.setString(1, medicinePart);
            rSet = statement.executeQuery();
            while (rSet.next()) {
                int medicineId = rSet.getInt(1);
                String naming = rSet.getString(2);
                String medicineFormTypeName = rSet.getString(3);
                double mainComponentMg = rSet.getDouble(4);
                double dosageMl = rSet.getDouble(5);

                String medicineName;
                if (dosageMl != 0) {
                    medicineName = naming + AppConstant.ONE_WHITESPACE
                            + medicineFormTypeName + AppConstant.ONE_WHITESPACE
                            + mainComponentMg + AppConstant.ONE_WHITESPACE
                            + AppConstant.SLASH + AppConstant.ONE_WHITESPACE
                            + dosageMl + AppConstant.ONE_WHITESPACE;
                } else {
                    medicineName = naming + AppConstant.ONE_WHITESPACE
                            + medicineFormTypeName + AppConstant.ONE_WHITESPACE
                            + mainComponentMg;
                }

                medicines.add(new AjaxMedicine(medicineId, medicineName));
            }

        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool during ajax fetching of " +
                    "AjaxMedicine", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching " +
                    "AjaxMedicine from DBs", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return medicines;
    }

    @Override
    public int fetchAmountOfPagesOfReferences(AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(AjaxSqlStatement
                    .SELECT_NUMBER_OF_REFUFAL_REFERENCES_OF_DOCTOR);
            statement.setInt(1, user.getStaffId());
            rSet = statement.executeQuery();
            if (rSet.next()) {
                return calculateAmoutOfPages(rSet);
            }

        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching " +
                    "amount of pages of references", e);
        } finally {
            pool.closeConnection(conn, statement, rSet);
        }

        return 0;
    }

    @Override
    public int fetchAmountOfPagesOfReferencesForPatient(AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = pool.takeConnection();
            statement = conn.prepareStatement(AjaxSqlStatement
                    .SELECT_NUMBER_OF_REFUFAL_REFERENCES_OF_PATIENT);
            statement.setInt(1, user.getUserId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return calculateAmoutOfPages(resultSet);
            }

        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from" +
                    "the connection pool", e);
        } catch (SQLException e) {
            throw new DaoException("An error while ajax fetching " +
                    "amount of pages of references", e);
        } finally {
            pool.closeConnection(conn, statement, resultSet);
        }

        return 0;
    }

    private int calculateAmoutOfPages(ResultSet resultSet) throws SQLException {
        int referencesAmount = resultSet.getInt(1);
        if (referencesAmount == 0) {
            return 0;
        }

        int quotient = referencesAmount / AppConstant.REFERENCES_PER_PAGE;
        if (quotient >= 1) {
            int remainder = referencesAmount % AppConstant
                    .REFERENCES_PER_PAGE;
            return (remainder > 0) ? ++quotient
                                   : quotient;
        } else {
            return 1;
        }
    }
}
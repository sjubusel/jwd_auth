package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.AuthUserDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.AuthenticationInfo;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;
import by.epamtc.jwd.auth.model.auth_info.Role;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.SqlStatement;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DefaultAuthUserDao implements AuthUserDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public AuthUser receiveAuthUserIfCorrect(AuthenticationInfo authInfo)
            throws DaoException {
        Connection conn = null;
        PreparedStatement selectAuthUserFromDataBaseQuery = null;
        ResultSet rSet = null;

        try {
            conn = pool.takeConnection();
            selectAuthUserFromDataBaseQuery = conn
                    .prepareStatement(SqlStatement.SELECT_AUTH_USER);
            selectAuthUserFromDataBaseQuery.setString(1, authInfo.getLogin());
            rSet = selectAuthUserFromDataBaseQuery.executeQuery();
            if (rSet.next()) {
                String dbPassword = rSet.getString(8);
                if (isPasswordCorrect(authInfo.getPassword(), dbPassword)) {
                    return compileAuthUser(rSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("An error while fetching data from DB " +
                    "(auth_user)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during logination", e);
        } finally {
            pool.closeConnection(conn, selectAuthUserFromDataBaseQuery, rSet);
        }

        return null;
    }

    @Override
    public AuthUser saveAuthUser(RegistrationInfo registrationInfo) throws DaoException {
        Connection conn = null;
        // TODO make an array of PreparedStatements
        List<PreparedStatement> statements = new ArrayList<>(2);
//        PreparedStatement stat = null;

        try {
            conn = pool.takeConnection();
            conn.setAutoCommit(false);
            // TODO replace queries with constants
            PreparedStatement personCreationStat = conn.prepareStatement(
                    "INSERT INTO hospital.persons (email, phone_number, first_name, middle_name, last_name, birth_date, gender) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            personCreationStat.setString(1, registrationInfo.getEmail());
            personCreationStat.setString(2, registrationInfo.getPhoneNumber());
            personCreationStat.setString(3, registrationInfo.getFirstName());
            personCreationStat.setString(4, registrationInfo.getMiddleName());
            personCreationStat.setString(5, registrationInfo.getLastName());
            personCreationStat.setObject(6, registrationInfo.getBirthday());
            personCreationStat.setString(7, registrationInfo.getGender().getGenderName());
            statements.add(personCreationStat);
            personCreationStat.executeUpdate();

            int personId = AppConstant.AUTH_USER_STANDARD_INT_VALUE;
            ResultSet personsGeneratedKeys = personCreationStat.getGeneratedKeys();
            while (personsGeneratedKeys.next()) {
                personId = personsGeneratedKeys.getInt(1);
            }
            // TODO replace queries with constants
            PreparedStatement authUserCreationStat = conn.prepareStatement(
                    "INSERT INTO hospital.auth_user (login, password, role_id, person_id) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            authUserCreationStat.setString(1, registrationInfo.getLogin());
            authUserCreationStat.setString(2, registrationInfo.getPassword());
            authUserCreationStat.setInt(3, Role.USER.getRoleId());
            authUserCreationStat.setInt(4, personId);
            statements.add(authUserCreationStat);
            authUserCreationStat.executeUpdate();

            int authUserId = AppConstant.AUTH_USER_STANDARD_INT_VALUE;
            ResultSet authGeneratedKeys = authUserCreationStat.getGeneratedKeys();
            while (authGeneratedKeys.next()) {
                authUserId = authGeneratedKeys.getInt(1);
            }

            conn.commit();
            // TODO replace queries with constants
            PreparedStatement selectAuthUserFromDataBase = conn.prepareStatement("SELECT au.id, p.first_name, p.middle_name, p.last_name, aur.auth_user_role_name, au.person_id, au.staff_id " +
                    "FROM hospital.auth_user au " +
                    "         JOIN hospital.persons p ON au.person_id = p.person_id " +
                    "         JOIN hospital.auth_user_roles aur ON au.role_id = aur.auth_user_role_id " +
                    "WHERE au.login = ?");
            selectAuthUserFromDataBase.setString(1, registrationInfo.getLogin());
            statements.add(selectAuthUserFromDataBase);
            ResultSet resultSet = selectAuthUserFromDataBase.executeQuery();
            if (resultSet.next()) {
                int authId = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String middleName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                Role role = Role.valueOf(resultSet.getString(5));
                int userId = resultSet.getInt(6);
                int staffId = resultSet.getInt(7);
                return new AuthUser(authId, firstName, middleName, lastName,
                        role, userId, staffId);
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                //TODO log4j
                ex.printStackTrace();
            }
            throw new DaoException("An error while saving AuthUser", e);
        } catch (ConnectionPoolException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                //TODO log4j
                ex.printStackTrace();
            }
            throw new DaoException("An error while taking a connection from " +
                    "connection pool during saving AuthUser", e);
        } finally {
            // TODO add a method with Array-param, which closes an array of Prep-statements
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (PreparedStatement statement : statements) {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // TODO ??? throw DaoException
        return null;
    }

    @Override
    public boolean containsLogin(String login) throws DaoException {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = pool.takeConnection();
            stat = conn.prepareStatement("SELECT login FROM " +
                    "hospital.auth_user WHERE login = ?");
            stat.setString(1, login);
            return stat.executeQuery().next();
        } catch (SQLException e) {
            throw new DaoException("An error while fetching login from " +
                    "DB(auth_user", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during login verification", e);
        } finally {
            pool.closeConnection(conn, stat);
        }
    }

    @Override
    public boolean containsEmail(String email) throws DaoException {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = pool.takeConnection();
            stat = conn.prepareStatement("SELECT email FROM " +
                    "hospital.persons WHERE email = ?");
            stat.setString(1, email);
            return stat.executeQuery().next();
        } catch (SQLException e) {
            throw new DaoException("An error while fetching email from " +
                    "DB(auth_user", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during email verification", e);
        } finally {
            pool.closeConnection(conn, stat);
        }
    }

    private boolean isPasswordCorrect(String plainPassword, String dbPassword) {
        return BCrypt.checkpw(plainPassword, dbPassword);
    }

    private AuthUser compileAuthUser(ResultSet resultSet) throws SQLException {
        int authId = resultSet.getInt(1);
        String firstName = resultSet.getString(2);
        String middleName = resultSet.getString(3);
        String lastName = resultSet.getString(4);
        Role role = Role.valueOf(resultSet.getString(5));
        int userId = resultSet.getInt(6);
        int staffId = resultSet.getInt(7);
        return new AuthUser(authId, firstName, middleName, lastName,
                role, userId, staffId);
    }
}

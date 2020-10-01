package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.AuthUserDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.dao.pool.ConnectionPool;
import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.model.auth_user.AuthUser;
import by.epamtc.jwd.auth.model.auth_user.Role;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class DefaultAuthUserDao implements AuthUserDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public AuthUser receiveAuthUserIfCorrect(String login, byte[] password)
            throws DaoException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rSet = null;

        try {
            conn = pool.takeConnection();
            return receiveAuthUserIfCorrectFromDb(login, password,
                    conn, stat, rSet);
        } catch (SQLException e) {
            throw new DaoException("An error while fetching data from DB " +
                    "(auth_user)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during logination", e);
        } finally {
            pool.closeConnection(conn, stat, rSet);
        }
    }

    @Override
    public int saveAuthUser(AuthUser user) throws DaoException {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            conn = pool.takeConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT " +
                    "INTO hospital.stub_auth_user " +
                    "(login, password, role)" +
                    "VALUES (?,?,?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().toString());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while inserting data into DB " +
                    "(auth_user)", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("An error while taking a connection from " +
                    "the connection pool during registration", e);
        } finally {
            pool.closeConnection(conn, stat);
        }
    }

    @Override
    public boolean containsLogin(String login) throws DaoException {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            conn = pool.takeConnection();
            stat = conn.prepareStatement("SELECT login " +
                    "FROM hospital.stub_auth_user " +
                    "WHERE login = ?");
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

    private AuthUser receiveAuthUserIfCorrectFromDb(String login, byte[] pass,
            Connection conn, PreparedStatement stat, ResultSet rSet)
            throws SQLException {
        stat = conn.prepareStatement("SELECT id, login, password, role," +
                " staff_id, person_id FROM hospital.stub_auth_user " +
                "WHERE login = ?");
        stat.setString(1, login);

        rSet = stat.executeQuery();
        if (rSet.next()) {
            return compileAuthUserIfPasswordIsCorrect(pass, rSet);
        }

        return null;
    }

    private AuthUser compileAuthUserIfPasswordIsCorrect(byte[] password,
            ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String loginDb = resultSet.getString("login");
        String passwordDb = resultSet.getString("password");
        if (!Arrays.equals(password, passwordDb.getBytes(StandardCharsets.UTF_8))) {
            return null;
        }
        Role role = Role.valueOf(resultSet.getString("role"));
        int staffId = resultSet.getInt("staff_id");
        int userId = resultSet.getInt("person_id");
        return new AuthUser(id, loginDb, role, staffId, userId);
    }
}

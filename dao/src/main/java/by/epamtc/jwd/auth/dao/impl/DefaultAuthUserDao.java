package by.epamtc.jwd.auth.dao.impl;

import by.epamtc.jwd.auth.dao.AuthUserDao;
import by.epamtc.jwd.auth.dao.SourceConnector;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_user.AuthUser;
import by.epamtc.jwd.auth.model.auth_user.Role;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class DefaultAuthUserDao implements AuthUserDao {
    private SourceConnector sourceConnector = DataBaseSourceConnector.getInstance();

    @Override
    public AuthUser receiveAuthUserIfCorrect(String login, byte[] password)
            throws DaoException {
        try (Connection connection = sourceConnector.getConnection()) {
            return receiveAuthUserIfCorrectFromDb(connection, login, password);
        } catch (SQLException e) {
            throw new DaoException("An error while fetching data from DB " +
                    "(auth_user)", e);
        }
    }

    @Override
    public int saveAuthUser(AuthUser user) throws DaoException {
        try (Connection connection = sourceConnector.getConnection()) {
            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO hospital.stub_auth_user" +
                            " (login, password, role)" +
                            "VALUES (?,?,?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().toString());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An error while inserting data into DB " +
                    "(auth_user)", e);
        }
    }

    @Override
    public boolean containsLogin(String login) throws DaoException {
        try (Connection connection = sourceConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT login FROM hospital.stub_auth_user " +
                            "WHERE login = ?");
            statement.setString(1, login);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            throw new DaoException("An error while fetching login from " +
                    "DB(auth_user", e);
        }
    }

    private AuthUser receiveAuthUserIfCorrectFromDb(Connection connection,
            String login, byte[] password) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("SELECT id, login, password, role," +
                        " staff_id, person_id " +
                        "FROM hospital.stub_auth_user " +
                        "WHERE login = ?");
        statement.setString(1, login);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return compileAuthUserIfPasswordIsCorrect(password, resultSet);
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
        return new AuthUser(id, loginDb, passwordDb, role, staffId, userId);
    }
}

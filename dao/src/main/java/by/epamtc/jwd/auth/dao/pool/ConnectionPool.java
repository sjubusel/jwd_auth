package by.epamtc.jwd.auth.dao.pool;

import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.dao.pool.util.DbParameter;
import by.epamtc.jwd.auth.dao.pool.util.DbResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class ConnectionPool {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool
            .class);
    private static volatile ConnectionPool instance;

    private String driverName;
    private String url;
    private String login;
    private String password;
    private int poolSize;

    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> givenConnections;


    private ConnectionPool() {
        DbResourceManager resManager = DbResourceManager.getInstance();
        this.driverName = resManager.getValue(DbParameter.DB_DRIVER);
        this.url = resManager.getValue(DbParameter.DB_URL);
        this.login = resManager.getValue(DbParameter.DB_LOGIN);
        this.password = resManager.getValue(DbParameter.DB_PASSWORD);
        String strPoolSize = resManager.getValue(DbParameter.DB_POOL_SIZE);

        try {
            this.poolSize = Integer.parseInt(strPoolSize);
        } catch (NumberFormatException e) {
            this.poolSize = 5;
        }
        try {
            initPoolData();
        } catch (ConnectionPoolException e) {
            logger.error("Error occurred while ConnectionPool initialization,\n" +
                    "the message of a src Exception is following:\n" +
                    "\"{}\"", e.getCause().getMessage(), e);
        }
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = freeConnections.take();
            givenConnections.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the " +
                    "source of data", e);
        }
        return connection;
    }

    public void dispose() {
        clearConnectionQueue();
    }

    public void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error("An error occurred while closing of a result set\n" +
                    "in ConnectionPool.\n" +
                    "ResultSet: {}", resultSet, e);
        }
    }

    public void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("An error occurred while closing of a java.sql." +
                    "Statement\nin ConnectionPool\n" +
                    "Statement: \"{}\"", statement, e);
        }

    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("An error occurred while closing of a Connection\n" +
                    "in ConnectionPool.\n" +
                    "Connection: \"{}\"", connection, e);
        }
    }

    public void closeConnection(Connection connection, Statement statement,
            ResultSet resultSet) {
        closeResultSet(resultSet);
        closeConnection(connection, statement);
    }

    public void closeConnection(Connection connection, Statement statement) {
        closeStatement(statement);
        closeConnection(connection);
    }

    public void closeConnection(Connection connection, ArrayList<PreparedStatement> statements) {
        for (PreparedStatement statement : statements) {
            closeStatement(statement);
        }
        closeConnection(connection);
    }

    public void closeConnection(Connection connection, Statement[] statements,
            ResultSet resultSet) {
        closeResultSet(resultSet);
        for (Statement statement : statements) {
            closeStatement(statement);
        }
        closeConnection(connection);
    }

    public void closeConnection(Connection connection, Statement[] statements,
            ResultSet[] resultSets) {
        for (ResultSet resultSet : resultSets) {
            closeResultSet(resultSet);
        }
        for (Statement statement : statements) {
            closeStatement(statement);
        }
        closeConnection(connection);
    }

    public void rollBackTransaction(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            logger.error("An error occurred while rolling back of changes \n" +
                    "performed by Connection in ConnectionPool.\n" +
                    "Connection: \"{}\"", conn, e);
        }
    }

    private void initPoolData() throws ConnectionPoolException {
        Locale.setDefault(Locale.US);

        try {
            Class.forName(driverName);
            givenConnections = new ArrayBlockingQueue<>(poolSize);
            freeConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, login,
                        password);
                PooledConnection pooledConnection
                        = new PooledConnection(connection);
                freeConnections.add(pooledConnection);

            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQLException in conn-pool", e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("DB Driver is not found", e);
        }
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(freeConnections);
            closeConnectionsQueue(givenConnections);
        } catch (SQLException e) {
            logger.error("An error while clearing Connection Queues:\n" +
                            "FreeConnections: \"{}\"\n" +
                            "Given Connections: \"{}\".", freeConnections,
                    givenConnections, e);
        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> connQueue)
            throws SQLException {
        Connection connection;
        while ((connection = connQueue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    private class PooledConnection implements Connection {
        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException("Attempting to close closed connection.");
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }

            if (!givenConnections.remove(this)) {
                throw new SQLException(
                        "Error deleting connection from the given " +
                                "away connections pool.");
            }

            if (!freeConnections.offer(this)) {
                throw new SQLException(
                        "Error allocating connection in the pool.");
            }
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements)
                throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public Statement createStatement(int resultSetType,
                int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType,
                    resultSetConcurrency);
        }

        @Override
        public Statement createStatement(int resultSetType,
                int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes)
                throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType,
                int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType,
                int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql)
                throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                int resultSetType, int resultSetConcurrency,
                int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public void setClientInfo(String name, String value)
                throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public void abort(Executor arg0) throws SQLException {
            connection.abort(arg0);

        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void releaseSavepoint(Savepoint arg0) throws SQLException {
            connection.releaseSavepoint(arg0);
        }

        @Override
        public void rollback(Savepoint arg0) throws SQLException {
            connection.rollback(arg0);
        }

        @Override
        public void setClientInfo(Properties arg0)
                throws SQLClientInfoException {
            connection.setClientInfo(arg0);
        }

        @Override
        public void setNetworkTimeout(Executor arg0, int arg1)
                throws SQLException {
            connection.setNetworkTimeout(arg0, arg1);
        }

        @Override
        public void setSchema(String arg0) throws SQLException {
            connection.setSchema(arg0);
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
            connection.setTypeMap(arg0);
        }

    }
}

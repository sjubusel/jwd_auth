package by.epamtc.jwd.auth.dao.pool;

import by.epamtc.jwd.auth.dao.pool.exception.ConnectionPoolException;
import by.epamtc.jwd.auth.dao.pool.util.DbParameter;
import by.epamtc.jwd.auth.dao.pool.util.DbResourceManager;
import by.epamtc.jwd.auth.model.constant.AppConstant;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

/**
 * a class which operates re-usage of  java.sql.Connection in order to
 * facilitate usage of an executing machine
 */
public final class ConnectionPool {
    /**
     * a static field which is responsible for logging of events within
     * this class
     */
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool
            .class);

    /**
     * a unique and only instance of this class
     */
    private static volatile ConnectionPool instance;

    /**
     * a field which represents a type of JDBC driver
     */
    private String driverName;

    /**
     * a field that represents a path for the source
     */
    private String url;

    /**
     * a field which is a credential
     */
    private String login;

    /**
     * a field which is a credential
     */
    private String password;

    /**
     * a field which contains a number of possible just-in-time Connection(s)
     */
    private int poolSize;

    /**
     * a BlockingQueue object which contains free-to-use connections
     */
    private BlockingQueue<Connection> freeConnections;

    /**
     * a BlockingQueue object which contains connections that is already
     * in use
     */
    private BlockingQueue<Connection> givenConnections;

    /**
     * a private constructor that initialize ConnectionPool
     */
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
            this.poolSize = AppConstant.DEFAULT_POOL_SIZE;
        }
        try {
            initPoolData();
        } catch (ConnectionPoolException e) {
            logger.error("Error occurred while ConnectionPool initialization,\n" +
                    "the message of a src Exception is following:\n" +
                    "\"{}\"", e.getCause().getMessage(), e);
        }
    }

    /**
     * a method which initialize and provide thread safe instance of a
     * current type
     *
     * @return an unique and only instance of a current class
     */
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

    /**
     * a method which provides ready-to-use Connection by replacing it from
     * <code>private BlockingQueue<Connection> freeConnections</code>
     * into <code>private BlockingQueue<Connection> givenConnections</code>
     *
     * @return a ready-to-use Connection
     * @throws ConnectionPoolException throws if some thead-related
     *                                 issues occur
     */
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

    /**
     * a method which disposes all connections
     */
    @SuppressWarnings("unused")
    public void dispose() {
        clearConnectionQueue();
    }

    /**
     * a method which closes java.sql.ResultSet
     *
     * @param resultSet java.sql.ResultSet that is to be closed
     */
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

    /**
     * a method which closes java.sql.Statement
     *
     * @param statement java.sql.Statement that is to be closed
     */
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

    /**
     * a method which closes java.sql.Connection
     *
     * @param connection java.sql.Connection that is to be closed
     */
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

    /**
     * a method which closes java.sql.Connection, java.sql.Statement,
     * java.sql.ResultSet
     *
     * @param connection java.sql.Connection that is to be closed
     * @param statement  java.sql.Statement that is to be closed
     * @param resultSet  java.sql.ResultSet that is to be closed
     */
    public void closeConnection(Connection connection, Statement statement,
            ResultSet resultSet) {
        closeResultSet(resultSet);
        closeConnection(connection, statement);
    }

    /**
     * a method which closes java.sql.Connection, java.sql.Statement,
     *
     * @param connection java.sql.Connection that is to be closed
     * @param statement  java.sql.Statement that is to be closed
     */
    public void closeConnection(Connection connection, Statement statement) {
        closeStatement(statement);
        closeConnection(connection);
    }

    /**
     * a method which closes java.sql.Connection, an array list
     * of java.sql.Statement,
     *
     * @param connection java.sql.Connection that is to be closed
     * @param statements an array list of java.sql.Statement that is to be closed
     */
    public void closeConnection(Connection connection,
            ArrayList<PreparedStatement> statements) {
        for (PreparedStatement statement : statements) {
            closeStatement(statement);
        }
        closeConnection(connection);
    }

    /**
     * a method which closes java.sql.Connection, an array of java.sql.Statement,
     * java.sql.ResultSet
     *
     * @param connection java.sql.Connection that is to be closed
     * @param statements an array of java.sql.Statement that is to be closed
     * @param resultSet  java.sql.ResultSet that is to be closed
     */
    public void closeConnection(Connection connection, Statement[] statements,
            ResultSet resultSet) {
        closeResultSet(resultSet);
        for (Statement statement : statements) {
            closeStatement(statement);
        }
        closeConnection(connection);
    }

    /**
     * a method which closes java.sql.Connection, an array of java.sql.Statement,
     * an array of java.sql.ResultSet
     *
     * @param connection java.sql.Connection that is to be closed
     * @param statements an array of java.sql.Statement that is to be closed
     * @param resultSets an array of java.sql.ResultSet that is to be closed
     */
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

    /**
     * a method which closes java.sql.Connection, an array of java.sql.Statement,
     * a list of java.sql.ResultSet
     *
     * @param connection java.sql.Connection that is to be closed
     * @param statements an array of java.sql.Statement that is to be closed
     * @param resultSets a list of java.sql.ResultSet that is to be closed
     */
    public void closeConnection(Connection connection, Statement[] statements,
            List<ResultSet> resultSets) {
        for (ResultSet resultSet : resultSets) {
            closeResultSet(resultSet);
        }
        for (Statement statement : statements) {
            closeStatement(statement);
        }
        closeConnection(connection);
    }

    /**
     * a method that roll back uncommitted changes in a database
     *
     * @param conn java.sql.Connection which is used to connect to a database
     *             and revert aforementioned changes
     */
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

    /**
     * a private method which initialize ConnectionPool with inner configuration
     *
     * @throws ConnectionPoolException throws if some SQLException and
     *                                 ClassNotFoundException occur in order
     *                                 to inform a client method about
     *                                 initialization problems
     */
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

    /**
     * a private method which really release java.sql.Connection (s)
     * sentenced in <code>private BlockingQueue<Connection> freeConnections</code>
     * and in <code>private BlockingQueue<Connection> givenConnections</code>
     */
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

    /**
     * a method which readly release java.sql.Connection (s) stored in
     * <code>java.util.concurrent.BlockingQueue</code>
     *
     * @param connQueue <code>java.util.concurrent.BlockingQueue</code> which
     *                  connections are to be released
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     */
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

    /**
     * an inner class which implement java.sql.Connection inferface
     * and configures it in compliance with needs of current ConnectionPool
     */
    private class PooledConnection implements Connection {
        /**
         * a field on which actions are being performed
         */
        private Connection connection;

        /**
         * a constructor
         *
         * @param c java.sql.Connection which is to become a main field
         * @throws SQLException throws if some issues with database interaction
         *                      occur
         */
        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        /**
         * a method which really releases java.sql.Connection
         *
         * @throws SQLException throws if some issues with database interaction
         *                      occur
         */
        public void reallyClose() throws SQLException {
            connection.close();
        }

        /**
         * an overridden method which clears all warnings reported for this
         * <code>PooledConnection</code> object.
         *
         * @throws SQLException throws if some issues with database interaction
         *                      occur
         */
        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        /**
         * a method which really closes PooledConnection
         *
         * @throws SQLException throws if some issues with database interaction
         *                      occur
         */
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

        /**
         * a method which commits uncommited changes performed by this
         * <code>PooledConnection</code>
         *
         * @throws SQLException throws if some issues with database interaction
         *                      occur
         */
        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        /**
         * @see java.sql.Connection#createArrayOf(String, Object[])
         */
        @Override
        public Array createArrayOf(String typeName, Object[] elements)
                throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        /**
         * @see java.sql.Connection#createBlob()
         */
        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        /**
         * @see java.sql.Connection#createClob()
         */
        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        /**
         * @see java.sql.Connection#createNClob()
         */
        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        /**
         * @see java.sql.Connection#createSQLXML()
         */
        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        /**
         * @see java.sql.Connection#createStatement()
         */
        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        /**
         * @see java.sql.Connection#createStatement(int resultSetType, int resultSetConcurrency)
         */
        @Override
        public Statement createStatement(int resultSetType,
                int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType,
                    resultSetConcurrency);
        }

        /**
         * @see java.sql.Connection#createStatement(int, int, int)
         */
        @Override
        public Statement createStatement(int resultSetType,
                int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        /**
         * @see java.sql.Connection#createStruct(String, Object[])
         */
        @Override
        public Struct createStruct(String typeName, Object[] attributes)
                throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        /**
         * @see java.sql.Connection#getAutoCommit()
         */
        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        /**
         * @see java.sql.Connection#getCatalog()
         */
        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        /**
         * @see java.sql.Connection#getClientInfo()
         */
        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        /**
         * @see java.sql.Connection#getClientInfo(String)
         */
        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        /**
         * @see java.sql.Connection#getHoldability()
         */
        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        /**
         * @see java.sql.Connection#getMetaData()
         */
        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        /**
         * @see java.sql.Connection#getTransactionIsolation()
         */
        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        /**
         * @see java.sql.Connection#getTypeMap()
         */
        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        /**
         * @see java.sql.Connection#getWarnings()
         */
        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        /**
         * @see java.sql.Connection#isClosed()
         */
        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        /**
         * @see java.sql.Connection#isReadOnly()
         */
        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        /**
         * @see java.sql.Connection#isValid(int)
         */
        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        /**
         * @see java.sql.Connection#nativeSQL(String)
         */
        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        /**
         * @see java.sql.Connection#prepareCall(String)
         */
        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        /**
         * @see java.sql.Connection#prepareCall(String, int, int)
         */
        @Override
        public CallableStatement prepareCall(String sql, int resultSetType,
                int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency);
        }

        /**
         * @see java.sql.Connection#prepareCall(String, int, int, int)
         */
        @Override
        public CallableStatement prepareCall(String sql, int resultSetType,
                int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        /**
         * @see java.sql.Connection#prepareStatement(String)
         */
        @Override
        public PreparedStatement prepareStatement(String sql)
                throws SQLException {
            return connection.prepareStatement(sql);
        }

        /**
         * @see java.sql.Connection#prepareStatement(String, int)
         */
        @Override
        public PreparedStatement prepareStatement(String sql,
                int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        /**
         * @see java.sql.Connection#prepareStatement(String, int[])
         */
        @Override
        public PreparedStatement prepareStatement(String sql,
                int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        /**
         * @see java.sql.Connection#prepareStatement(String, String[])
         */
        @Override
        public PreparedStatement prepareStatement(String sql,
                String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        /**
         * @see java.sql.Connection#prepareCall(String, int, int)
         */
        @Override
        public PreparedStatement prepareStatement(String sql,
                int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency);
        }

        /**
         * @see java.sql.Connection#prepareStatement(String, int, int, int)
         */
        @Override
        public PreparedStatement prepareStatement(String sql,
                int resultSetType, int resultSetConcurrency,
                int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        /**
         * @see java.sql.Connection#rollback()
         */
        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        /**
         * @see java.sql.Connection#setAutoCommit(boolean)
         */
        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        /**
         * @see java.sql.Connection#setCatalog(String)
         */
        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        /**
         * @see java.sql.Connection#setClientInfo(String, String)
         */
        @Override
        public void setClientInfo(String name, String value)
                throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        /**
         * @see java.sql.Connection#setHoldability(int)
         */
        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        /**
         * @see java.sql.Connection#setReadOnly(boolean)
         */
        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        /**
         * @see java.sql.Connection#setSavepoint()
         */
        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        /**
         * @see java.sql.Connection#setSavepoint(String)
         */
        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        /**
         * @see java.sql.Connection#setTransactionIsolation(int)
         */
        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        /**
         * @see java.sql.Connection#isWrapperFor(Class)
         */
        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        /**
         * @see java.sql.Connection#unwrap(Class)
         */
        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        /**
         * @see java.sql.Connection#abort(Executor)
         */
        @Override
        public void abort(Executor arg0) throws SQLException {
            connection.abort(arg0);

        }

        /**
         * @see java.sql.Connection#getNetworkTimeout()
         */
        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        /**
         * @see java.sql.Connection#getSchema()
         */
        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        /**
         * @see java.sql.Connection#releaseSavepoint(Savepoint)
         */
        @Override
        public void releaseSavepoint(Savepoint arg0) throws SQLException {
            connection.releaseSavepoint(arg0);
        }

        /**
         * @see java.sql.Connection#rollback(Savepoint)
         */
        @Override
        public void rollback(Savepoint arg0) throws SQLException {
            connection.rollback(arg0);
        }

        /**
         * @see java.sql.Connection#setClientInfo(Properties)
         */
        @Override
        public void setClientInfo(Properties arg0)
                throws SQLClientInfoException {
            connection.setClientInfo(arg0);
        }

        /**
         * @see java.sql.Connection#setNetworkTimeout(Executor, int)
         */
        @Override
        public void setNetworkTimeout(Executor arg0, int arg1)
                throws SQLException {
            connection.setNetworkTimeout(arg0, arg1);
        }

        /**
         * @see java.sql.Connection#setSchema(String)
         */
        @Override
        public void setSchema(String arg0) throws SQLException {
            connection.setSchema(arg0);
        }

        /**
         * @see java.sql.Connection#setTypeMap(Map)
         */
        @Override
        public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
            connection.setTypeMap(arg0);
        }

    }
}

package by.bsuir.lab2.dao.connection;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class PooledConnection implements Connection {
    private final Connection connection;

    public PooledConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws SQLException {
        if (connection.isClosed()) {
            throw new SQLException("Attempt of closing of the closed DB connection.");
        }
        if (!connection.isReadOnly()) {
            connection.setReadOnly(false);
        }
        connection.setAutoCommit(true);
        if (!ConnectionPool.getInstance().releaseConnection(this)) {
            throw new SQLException("Error during returning connection back to the connection pool.");
        }
    }
    
    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean b) throws SQLException {
        connection.setAutoCommit(b);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setReadOnly(boolean b) throws SQLException {
        connection.setReadOnly(b);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    @Override
    public void setCatalog(String sql) throws SQLException {
        connection.setCatalog(sql);
    }

    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int i) throws SQLException {
        connection.setTransactionIsolation(i);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    @Override
    public Statement createStatement(int i, int i1) throws SQLException {
        return connection.createStatement(i, i1);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int i, int i1) throws SQLException {
        return connection.prepareStatement(sql, i, i1);
    }

    @Override
    public CallableStatement prepareCall(String sql, int i, int i1) throws SQLException {
        return connection.prepareCall(sql, i, i1);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }

    @Override
    public void setHoldability(int i) throws SQLException {
        connection.setHoldability(i);
    }

    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String sql) throws SQLException {
        return connection.setSavepoint(sql);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback();
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int i, int i1, int i2) throws SQLException {
        return connection.createStatement(i, i1, i2);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int i, int i1, int i2) throws SQLException {
        return connection.prepareStatement(sql, i, i1, i2);
    }

    @Override
    public CallableStatement prepareCall(String sql, int i, int i1, int i2) throws SQLException {
        return connection.prepareCall(sql, i, i1, i2);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int i) throws SQLException {
        return connection.prepareStatement(sql, i);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] ints) throws SQLException {
        return connection.prepareStatement(sql, ints);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] strings) throws SQLException {
        return connection.prepareStatement(sql, strings);
    }

    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
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
    public boolean isValid(int i) throws SQLException {
        return connection.isValid(i);
    }

    @Override
    public void setClientInfo(String sql, String s1) throws SQLClientInfoException {
        connection.setClientInfo(sql, s1);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String sql) throws SQLException {
        return connection.getClientInfo(sql);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    @Override
    public Array createArrayOf(String sql, Object[] objects) throws SQLException {
        return connection.createArrayOf(sql, objects);
    }

    @Override
    public Struct createStruct(String sql, Object[] objects) throws SQLException {
        return connection.createStruct(sql, objects);
    }

    @Override
    public void setSchema(String sql) throws SQLException {
        connection.setSchema(sql);
    }

    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int i) throws SQLException {
        connection.setNetworkTimeout(executor, i);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return connection.unwrap(aClass);
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return connection.isWrapperFor(aClass);
    }
}
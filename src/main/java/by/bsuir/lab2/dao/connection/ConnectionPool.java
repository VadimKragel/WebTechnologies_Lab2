package by.bsuir.lab2.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private BlockingQueue<Connection> connections;
    private String driver;
    private String connectionString;
    private String username;
    private int poolSize;
    private static final ConnectionPool INSTANCE = new ConnectionPool();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public void init(Properties dbProperties) throws ConnectionPoolException {
        try {
            driver = dbProperties.getProperty(DatabaseConfiguration.DB_DRIVER);
            Class.forName(driver);
            connectionString = dbProperties.getProperty(DatabaseConfiguration.DB_CONNECTION_URL);
            username = dbProperties.getProperty(DatabaseConfiguration.DB_USERNAME);
            String password = dbProperties.getProperty(DatabaseConfiguration.DB_PASSWORD);
            poolSize = Integer.parseInt(dbProperties.getProperty(DatabaseConfiguration.DB_POOL_SIZE));
            connections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(connectionString, username, password);
                connections.add(new PooledConnection(connection));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException("Error during initialization of connection pool", e);
        }
    }

    public String getDriver() {
        return driver;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public String getUsername() {
        return username;
    }

    public int getPoolSize() {
        return poolSize;
    }
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error during extracting connection from connection pool", e);
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection){
        return connections.offer(connection);
    }

    public void close() throws ConnectionPoolException {
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = connections.take();
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                connection.unwrap(Connection.class).close();
            } catch (SQLException | InterruptedException e) {
                throw new ConnectionPoolException("Error during closing of the connection pool.", e);
            }
        }
    }
}

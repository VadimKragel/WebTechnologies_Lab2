package by.bsuir.lab2.dao.mysql;

import by.bsuir.lab2.dao.connection.ConnectionPool;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;

import java.sql.Connection;

public abstract class AbstractDAO {
    private Connection connection;

    public AbstractDAO() {
    }

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
    
    public Connection getConnection() throws ConnectionPoolException {
        if (connection == null) {
            return ConnectionPool.getInstance().getConnection();
        } else {
            return connection;
        }
    }

    public void closeResources(AutoCloseable... resources) throws Exception {
        for (AutoCloseable closeable: resources) {
            if (closeable != null){
                closeable.close();
            }
        }
    }
}

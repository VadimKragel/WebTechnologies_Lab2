package by.bsuir.lab2.dao.mysql;

import by.bsuir.lab2.dao.DAOManager;
import by.bsuir.lab2.dao.UserDAO;
import by.bsuir.lab2.dao.mysql.impl.MySqlUserDAOImpl;

public class MySqlDAOManager implements DAOManager {
    private static final UserDAO userDAO = new MySqlUserDAOImpl();

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }
}

package by.bsuir.lab2.dao.mysql;

import by.bsuir.lab2.dao.DAOManager;
import by.bsuir.lab2.dao.RoleDAO;
import by.bsuir.lab2.dao.UserDAO;
import by.bsuir.lab2.dao.mysql.impl.MySqlRoleDAOImpl;
import by.bsuir.lab2.dao.mysql.impl.MySqlUserDAOImpl;

public class MySqlDAOManager implements DAOManager {
    private static final UserDAO userDAO = new MySqlUserDAOImpl();
    private static final RoleDAO roleDAO = new MySqlRoleDAOImpl();

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    public RoleDAO getRoleDAO() {
        return roleDAO;
    }
}

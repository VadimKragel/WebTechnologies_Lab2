package by.bsuir.lab2.dao.mysql.impl;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.bean.dto.RegisterUserDTO;
import by.bsuir.lab2.dao.UserDAO;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.dao.mysql.AbstractDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDAOImpl extends AbstractDAO implements UserDAO {
    public static final Logger logger = LogManager.getLogger(MySqlUserDAOImpl.class);
    private static final String ADD_USER = "INSERT INTO `user` (`username`, `email`, `passwordHash`) VALUES (?, ?, ?)";
    public static final String GET_USER_BY_LOGIN = "SELECT `id`, `role_id`, `username`, `email`, `passwordHash`, `name`, " +
            "`surname`, `patronymic`, `birth_date` FROM `user` WHERE `email`=? OR `username`=?";
    public static final String GET_USER_BY_ID = "SELECT `id`, `role_id`, `username`, `email`, `passwordHash`, `name`, " +
            "`surname`, `patronymic`, `birth_date` FROM `user` WHERE `id`=?";
    public static final String GET_USER_NAME = "SELECT `name` FROM `user` WHERE `id`=?";
    private static final String IS_USERNAME_OR_EMAIL_EXIST = "SELECT EXISTS(SELECT 1 FROM `user` WHERE `username`=? OR `email`=?)";
    private static final String SET_USER_INFO = "";

    private static final String GET_ALL_USERS = "SELECT `user`.`id`, `user`.`role_id`, `user`.`username`, `user`.`email`, " +
            "`user`.`passwordHash`, `user`.`name`, `user`.`surname`, `user`.`patronymic`, `user`.`birth_date` FROM `user`";

    public MySqlUserDAOImpl() {
    }

    public MySqlUserDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int addUser(String username, String email, String passwordHash) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, passwordHash);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception of inserting user.");
        } finally {
            try {
                closeResources(connection, rs, stmt);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
    }

    @Override
    public boolean isUserExists(RegisterUserDTO registerUserDTO) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(IS_USERNAME_OR_EMAIL_EXIST);
            stmt.setString(1, registerUserDTO.getUsername());
            stmt.setString(2, registerUserDTO.getEmail());
            rs = stmt.executeQuery();
            rs.next();
            return rs.getBoolean(1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception on user existing check.");
        } finally {
            try {
                closeResources(rs, stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
    }

    @Override
    public User getUser(String login) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_USER_BY_LOGIN);
            stmt.setString(1, login);
            stmt.setString(2, login);
            rs = stmt.executeQuery();
            rs.next();
            return mapFullUser(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception of getting user by login.", e);
        } finally {
            try {
                closeResources(rs, stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
    }

    @Override
    public User getUserById(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_USER_BY_ID);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            rs.next();
            return mapFullUser(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting user by id", e);
        } finally {
            try {
                closeResources(rs, stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
    }

    @Override
    public String getUsersName(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_USER_NAME);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            rs.next();
            String name = rs.getString(1);
            return name;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting user's name from database.", e);
        } finally {
            try {
                closeResources(rs, stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
    }

    @Override
    public void setUserInfo(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(SET_USER_INFO);
//            stmt.setString(1, user.getName());
//            stmt.setString(2, user.getMiddleName());
//            stmt.setString(3, user.getSurname());
//            stmt.setString(4, user.getAdress());
//            stmt.setString(5, user.getPassport());
//            stmt.setString(6, user.getTelephone());
//            stmt.setInt(7, user.getIdUser());
            stmt.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during adding info about user to table 'client' in database.", e);
        } finally {
            try {
                closeResources(stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
    }

    @Override
    public List<User> getUsers() throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> users;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_ALL_USERS);
            rs = stmt.executeQuery();
            users = mapUsers(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting inmapation about all users.", e);
        } finally {
            try {
                closeResources(rs, stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
        return users;
    }

    private User mapFullUser(ResultSet rs) throws SQLException {
        if (rs.getRow() == 0) {
            return null;
        }
        User user = new User();
        user.setId(rs.getInt(1));
        user.setRoleId(rs.getInt(2));
        user.setUsername(rs.getString(3));
        user.setEmail(rs.getString(4));
        user.setPasswordHash(rs.getString(5));
        user.setName(rs.getString(6));
        user.setSurname(rs.getString(7));
        user.setPatronymic(rs.getString(8));
        user.setBirthDate(rs.getDate(9));
        return user;
    }

    private List<User> mapUsers(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = mapFullUser(rs);
            users.add(user);
        }
        return users;
    }

}

package by.bsuir.lab2.dao.mysql.impl;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.bean.dto.RegisterUserDTO;
import by.bsuir.lab2.bean.dto.UpdateUserDTO;
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
    private static final Logger logger = LogManager.getLogger(MySqlUserDAOImpl.class);

    private static final String ADD_USER = "INSERT INTO `user` (`username`, `email`, `passwordhash`) VALUES (?, ?, ?)";
    private static final String GET_USER_BY_LOGIN = "SELECT `u`.`id`, `r`.`id`, `r`.`name`, `u`.`username`, `u`.`email`, `u`.`passwordhash`, `u`.`name`, `u`.`surname`, `u`.`patronymic`, `u`.`birth_date` FROM `user` `u` JOIN `role` `r` ON `u`.`role_id` = `r`.`id`  WHERE `u`.`email`=? OR `u`.`username`=?";
    private static final String GET_USER_BY_ID = "SELECT `u`.`id`, `r`.`id`, `r`.`name`, `u`.`username`, `u`.`email`, `u`.`passwordhash`, `u`.`name`, `u`.`surname`, `u`.`patronymic`, `u`.`birth_date` FROM `user` `u` JOIN `role` `r` ON `u`.`role_id` = `r`.`id` WHERE `u`.`id`=?";
    private static final String IS_USERNAME_OR_EMAIL_EXIST = "SELECT EXISTS(SELECT 1 FROM `user` WHERE `username`=? OR `email`=?)";
    private static final String UPDATE_USER = "UPDATE `user` SET `role_id`=?, `username`=?, `email`=?, `name`=?, `surname`=?, `patronymic`=?, `birth_date`=? WHERE `id`=?";
    private static final String GET_ALL_USERS = "SELECT `u`.`id`, `r`.`id`, `r`.`name`, `u`.`username`, `u`.`email`, `u`.`passwordhash`, `u`.`name`, `u`.`surname`, `u`.`patronymic`, `u`.`birth_date` FROM `user` u JOIN `role` `r` ON `u`.`role_id` = `r`.`id`";

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
    public boolean updateUser(UpdateUserDTO updateUserDTO) throws IllegalStateException, DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        int rowUpdated;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(UPDATE_USER);
            stmt.setInt(1, updateUserDTO.getRoleId());
            stmt.setString(2, updateUserDTO.getUsername());
            stmt.setString(3, updateUserDTO.getEmail());
            stmt.setString(4, updateUserDTO.getName());
            stmt.setString(5, updateUserDTO.getSurname());
            stmt.setString(6, updateUserDTO.getPatronymic());
            stmt.setDate(7, updateUserDTO.getBirthDate());
            stmt.setInt(8, updateUserDTO.getId());
            rowUpdated = stmt.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during update user data in database.", e);
        } finally {
            try {
                closeResources(stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
        return rowUpdated > 0;
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
            throw new DAOException("Exception during getting information about all users.", e);
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
        Role role = new Role();
        role.setId(rs.getInt(2));
        role.setName(rs.getString(3));
        user.setRole(role);
        user.setUsername(rs.getString(4));
        user.setEmail(rs.getString(5));
        user.setPasswordHash(rs.getString(6));
        user.setName(rs.getString(7));
        user.setSurname(rs.getString(8));
        user.setPatronymic(rs.getString(9));
        user.setBirthDate(rs.getDate(10));
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

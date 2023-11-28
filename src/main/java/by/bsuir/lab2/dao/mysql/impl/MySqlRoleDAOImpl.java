package by.bsuir.lab2.dao.mysql.impl;

import by.bsuir.lab2.bean.EnumRole;
import by.bsuir.lab2.bean.Product;
import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.dao.RoleDAO;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.dao.mysql.AbstractDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlRoleDAOImpl extends AbstractDAO implements RoleDAO {
    public static final Logger logger = LogManager.getLogger(MySqlRoleDAOImpl.class);
    private static final String GET_ROLE_BY_ID = "SELECT `id`, `name` FROM `role` WHERE `id`=?";
    private static final String GET_ALL_ROLES = "SELECT `id`, `name` FROM `role`";

    @Override
    public List<Role> getRoles() throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_ALL_ROLES);
            rs = stmt.executeQuery();
            rs.next();
            return mapRoles(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting all roles.", e);
        } finally {
            try {
                closeResources(rs, stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
    }

    @Override
    public Role getRoleById(int roleId) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_ROLE_BY_ID);
            stmt.setInt(1, roleId);
            rs = stmt.executeQuery();
            rs.next();
            return mapRole(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception of getting product by id.", e);
        } finally {
            try {
                closeResources(rs, stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
    }

    private Role mapRole(ResultSet rs) throws SQLException {
        if (rs.getRow() == 0) {
            return null;
        }
        Role role = new Role();
        role.setId(rs.getInt(1));
        role.setName(rs.getString(2));
        return role;
    }

    private List<Role> mapRoles(ResultSet rs) throws SQLException {

        List<Role> roles = new ArrayList<>();
        while (rs.next()) {
            Role role= mapRole(rs);
            roles.add(role);
        }
        return roles;
    }
}

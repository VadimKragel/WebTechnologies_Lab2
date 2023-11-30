package by.bsuir.lab2.dao.mysql.impl;

import by.bsuir.lab2.bean.Product;
import by.bsuir.lab2.dao.ProductDAO;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.dao.mysql.AbstractDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductDAOImpl extends AbstractDAO implements ProductDAO {

    private static final Logger logger = LogManager.getLogger(MySqlProductDAOImpl.class);
    private static final String GET_PRODUCT_BY_ID = "SELECT `id`, `name`, `description`, `price`, `quantity` FROM `product` WHERE `id`=?";
    private static final String GET_ALL_PRODUCTS = "SELECT `id`, `name`, `description`, `price`, `quantity` FROM `product`";
    private static final String ADD_PRODUCT = "INSERT INTO `product` (`name`, `description`, `price`, `quantity`) VALUES (?, ?, ?, ?)";

    public MySqlProductDAOImpl() {
    }

    public MySqlProductDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Product> getProducts() throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Product> products;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_ALL_PRODUCTS);
            rs = stmt.executeQuery();
            products = mapProducts(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception of getting all products.", e);
        } finally {
            try {
                closeResources(rs, stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
        return products;
    }

    private List<Product> mapProducts(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            Product product = mapProduct(rs);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProductById(int productId) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_PRODUCT_BY_ID);
            stmt.setInt(1, productId);
            rs = stmt.executeQuery();
            rs.next();
            return mapProduct(rs);
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

    private Product mapProduct(ResultSet rs) throws SQLException
    {
        if (rs.getRow() == 0) {
            return null;
        }
        Product product = new Product();
        product.setId(rs.getInt(1));
        product.setName(rs.getString(2));
        product.setDescription(rs.getString(3));
        product.setPrice(rs.getInt(4));
        product.setQuantity(rs.getInt(5));
        return product;
    }

    @Override
    public int addProduct(Product product) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception of inserting product.", e);
        } finally {
            try {
                closeResources(rs, stmt, connection);
            } catch (Exception e) {
                logger.warn("Close database resources exception.", e);
            }
        }
    }
}

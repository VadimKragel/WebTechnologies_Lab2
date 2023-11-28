package by.bsuir.lab2.dao;

import by.bsuir.lab2.bean.Product;
import by.bsuir.lab2.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO {

    List<Product> getProducts() throws DAOException;

    Product getProductById(int productId) throws DAOException;

    int addProduct(Product product) throws DAOException;
}

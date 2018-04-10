package kz.sushi.dao.impl;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.Product;
import kz.sushi.dao.IProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProduct {
    private final static String PRODUCT_CREATE = "INSERT INTO product (productType_id, name, description, count, cost, id, locale_id, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String PRODUCT_UPDATE = "UPDATE product SET  productType_id=?, name=?, description=?,  count=?, cost=?, image=? WHERE id=? AND locale_id=?";
    private final static String PRODUCT_FIND_BY_ID_LOCALE_ID_PROD_TYPE_ID = "SELECT * FROM product WHERE id=? AND locale_id=? AND productType_id=?";
    private final static String PRODUCT_FIND_BY_LOCALE_ID_PROD_TYPE_ID = "SELECT * FROM product WHERE locale_id=? AND productType_id=?";
    private final static String PRODUCT_DELETE = "DELETE FROM product";
    private final static String PRODUCT_FIND_ALL = "SELECT * FROM product";
    private static ProductDAO productDAO;
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static ProductDAO getProductDAO() {
        if (productDAO == null) productDAO = new ProductDAO();
        return productDAO;
    }
    private List<Product> productList = new ArrayList<>();
    private Product product = new Product();

    @Override
    public void create(Product product) throws SQLException {
        setToPStatement(product, PRODUCT_CREATE);
    }

    @Override
    public void update(Product product) throws SQLException {
        setToPStatement(product, PRODUCT_UPDATE);
    }

    @Override
    public void delete(Product product) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(PRODUCT_DELETE)) {
            pStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Product> findAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(PRODUCT_FIND_ALL)) {
            while (resultSet.next()) {
                setToProduct(resultSet);
                productList.add(product);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return productList;
    }

    @Override
    public List<Product> findByIdLocIdProdTypeId(String  id, String locale, int type) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(PRODUCT_FIND_BY_ID_LOCALE_ID_PROD_TYPE_ID)) {
            pStatement.setString(1, id);
            pStatement.setString(2, locale);
            pStatement.setInt(3, type);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    setToProduct(resultSet);
                }
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return productList;
    }

    @Override
    public List<Product> findByLocIdAndProdTypeId(String locale, int prodTypeId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement pStatement = connection.prepareStatement(PRODUCT_FIND_BY_LOCALE_ID_PROD_TYPE_ID)) {
            pStatement.setString(1, locale);
            pStatement.setInt(2, prodTypeId);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    setToProduct(resultSet);
                    productList.add(product);
                }
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return productList;
    }

    public void setToProduct(ResultSet resultSet) throws SQLException {
        product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setLocaleId(resultSet.getInt("locale_id"));
        product.setProdTypeId(resultSet.getInt("productType_id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setCount(resultSet.getInt("count"));
        product.setCost(resultSet.getInt("cost"));
        product.setImage(resultSet.getString("image"));
        productList.add(product);
    }

    private void setToPStatement (Product product, String sqlCommand) throws SQLException{
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setInt(1, product.getProdTypeId());
            pStatement.setString(2, product.getName());
            pStatement.setString(3, product.getDescription());
            pStatement.setInt(4, product.getCount());
            pStatement.setInt(5, product.getCost());
            pStatement.setInt(6, product.getId());
            pStatement.setInt(7, product.getLocaleId());
            pStatement.setString(8, product.getImage());
            pStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}

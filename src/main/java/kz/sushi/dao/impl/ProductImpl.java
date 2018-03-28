package kz.sushi.dao.impl;

import kz.sushi.dao.entity.Product;
import kz.sushi.dao.IProduct;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements IProduct {
    private static Logger log = Logger.getLogger(ProductImpl.class.getName());
    final static String PRODUCT_CREATE = "INSERT INTO product (productType_id, name, description, count, cost, id, locale_id, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final static String PRODUCT_UPDATE = "UPDATE product SET  productType_id=?, name=?, description=?,  count=?, cost=?, image=? WHERE id=? AND locale_id=?";
    final static String PRODUCT_FIND_BY_ID_LOCALE_ID_PROD_TYPE_ID = "SELECT * FROM product WHERE id=? AND locale_id=? AND productType_id=?";
    final static String PRODUCT_FIND_BY_LOCALE_ID_PROD_TYPE_ID = "SELECT * FROM product WHERE locale_id=? AND productType_id=?";
    final static String PRODUCT_DELETE = "DELETE FROM product";
    final static String PRODUCT_FIND_ALL = "SELECT * FROM product";
    private static ProductImpl productImpl;
    private Connection connection;

    public ProductImpl(Connection connection) {
        this.connection = connection;
    }

    public static ProductImpl getProductImpl(Connection connection) {
        if (productImpl == null) productImpl = new ProductImpl(connection);
        return productImpl;
    }
    List<Product> productList = new ArrayList<>();

    @Override
    public void create(Product product) {
        setToPStatement(product, PRODUCT_CREATE);
    }

    @Override
    public void update(Product product) {
        setToPStatement(product, PRODUCT_UPDATE);
    }

    @Override
    public void delete(Product product) {
        try (PreparedStatement pStatement = connection.prepareStatement(PRODUCT_DELETE)) {
            pStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public List<Product> findAll() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(PRODUCT_FIND_ALL)) {
            setToProduct(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        return productList;
    }

    @Override
    public List<Product> findByIdLocIdProdTypeId(String  id, String locale, int type) {
        try (PreparedStatement pStatement = connection.prepareStatement(PRODUCT_FIND_BY_ID_LOCALE_ID_PROD_TYPE_ID)) {
            pStatement.setString(1, id);
            pStatement.setString(2, locale);
            pStatement.setInt(3, type);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                setToProduct(resultSet);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return productList;
    }

    @Override
    public List<Product> findByLocIdAndProdTypeId(String locale, int prodTypeId) {
        productList = new ArrayList<>();
        try (PreparedStatement pStatement = connection.prepareStatement(PRODUCT_FIND_BY_LOCALE_ID_PROD_TYPE_ID)) {
            pStatement.setString(1, locale);
            pStatement.setInt(2, prodTypeId);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                setToProduct(resultSet);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return productList;
    }

    public void setToProduct(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Product product = new Product();
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
    }

    private void setToPStatement (Product product, String sqlCommand){
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
        } catch (SQLException e) {
            log.error(e);
        }
    }
}

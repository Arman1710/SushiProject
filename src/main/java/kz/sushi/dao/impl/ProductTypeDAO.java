package kz.sushi.dao.impl;

import kz.sushi.dao.entity.ProductType;
import kz.sushi.dao.IProductType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDAO implements IProductType {
    final static String PRODUCT_TYPE_CREATE = "INSERT INTO product_type (type, id) VALUES (?, ?)";
    final static String PRODUCT_TYPE_UPDATE = "UPDATE product_type SET type=? WHERE id=?";
    final static String PRODUCT_TYPE_DELETE = "DELETE FROM product_type";
    final static String PRODUCT_TYPE_FIND_ALL = "SELECT * FROM product_type";
    final static String PRODUCT_TYPE_FIND_BY_TYPE = "SELECT * FROM product_type WHERE type=?";

    private Connection connection;

    public ProductTypeDAO(Connection connection) {
        this.connection = connection;
    }


    ProductType productType = new ProductType();
    List<ProductType> productTypeList = new ArrayList<>();

    @Override
    public void create(ProductType productType) throws SQLException {
        setToPStatement(productType, PRODUCT_TYPE_CREATE);
    }

    @Override
    public void update(ProductType productType) throws SQLException {
        setToPStatement(productType, PRODUCT_TYPE_UPDATE);
    }

    @Override
    public void delete(ProductType productType) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(PRODUCT_TYPE_DELETE)) {
            pStatement.executeUpdate();
        }
    }

    @Override
    public List<ProductType> findAll() throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(PRODUCT_TYPE_FIND_ALL)) {
            setToProdType(resultSet);
        }
        return productTypeList;
    }

    @Override
    public ProductType findProdType(String type) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(PRODUCT_TYPE_FIND_BY_TYPE)) {
            pStatement.setString(1, type);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    productType.setId(resultSet.getInt("id"));
                    productTypeList.add(productType);
                }
            }
        }
        return productType;
    }

    private void setToProdType(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            productType =new ProductType();
            productType.setId(resultSet.getInt("id"));
            productType.setType(resultSet.getString("type"));
            productTypeList.add(productType);
        }
    }

    private void setToPStatement (ProductType productType, String sqlCommand) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setString(1, productType.getType());
            pStatement.executeUpdate();
        }
    }
}

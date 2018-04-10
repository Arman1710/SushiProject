package kz.sushi.dao.impl;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.Item;
import kz.sushi.dao.IItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements IItem {

    private final static String ITEM_CREATE = "INSERT INTO item (product_id, orders_id) VALUES (?, ?)";
    private final static String ITEM_UPDATE = "UPDATE item SET product_id=?, orders_id=?, WHERE id=?";
    private final static String ITEM_DELETE = "DELETE FROM item";
    private final static String ITEM_FIND_ALL = "SELECT * FROM item";
    private final static String ITEM_FIND_BY_ORDER_ID = "SELECT * FROM item WHERE order_id=?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Item item = new Item();
    private List<Item> itemList = new ArrayList<>();

    @Override
    public void create(Item model) throws SQLException {

        setToPStatement(item, ITEM_CREATE);
    }

    @Override
    public void update(Item model) throws SQLException {
        setToPStatement(item, ITEM_UPDATE);
    }

    @Override
    public void delete(Item model) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(ITEM_DELETE)) {
            pStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Item> findAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ITEM_FIND_ALL)) {
            setToItem(resultSet);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return itemList;
    }

    @Override
    public List<Item> findItemByOrderId(int orderId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(ITEM_FIND_BY_ORDER_ID)) {
            pStatement.setInt(1, orderId);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                setToItem(resultSet);
            } finally {
                connectionPool.returnConnection(connection);
            }
            return itemList;
        }
    }

    @Override
    public void addToItem(int prodId, int ordersId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(ITEM_CREATE)) {
            pStatement.setInt(1, prodId);
            pStatement.setInt(2, ordersId);
            pStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }



    private void setToItem(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            item.setId(resultSet.getInt("id"));
            item.setId(resultSet.getInt("product_id"));
            item.setId(resultSet.getInt("order_id"));
            itemList.add(item);
        }
    }

    private void setToPStatement (Item item, String sqlCommand) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setInt(1, item.getProductId());
            pStatement.setInt(2, item.getOrderId());
            pStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}

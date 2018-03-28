package kz.sushi.dao.impl;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.Item;
import kz.sushi.dao.IItem;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemImpl implements IItem {
    private static Logger log = Logger.getLogger(ItemImpl.class.getName());
    final static String ITEM_CREATE = "INSERT INTO item (product_id, orders_id) VALUES (?, ?)";
    final static String ITEM_UPDATE = "UPDATE item SET product_id=?, orders_id=?, WHERE id=?";
    final static String ITEM_DELETE = "DELETE FROM item";
    final static String ITEM_FIND_ALL = "SELECT * FROM item";
    final static String ITEM_FIND_BY_ORDER_ID = "SELECT * FROM item WHERE order_id=?";
    private Connection connection;

    public ItemImpl(Connection connection) {
        this.connection = connection;
    }
    Item item = new Item();
    List<Item> itemList = new ArrayList<>();

    @Override
    public void create(Item model) {
        setToPStatement(item, ITEM_CREATE);
    }

    @Override
    public void update(Item model) {
        setToPStatement(item, ITEM_UPDATE);
    }

    @Override
    public void delete(Item model) {
        try (PreparedStatement pStatement = connection.prepareStatement(ITEM_DELETE)) {
            pStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public List<Item> findAll() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ITEM_FIND_ALL)) {
            setToItem(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        return itemList;
    }

    @Override
    public List<Item> findItemByOrderId(int orderId) {
        try (PreparedStatement pStatement = connection.prepareStatement(ITEM_FIND_BY_ORDER_ID)) {
            pStatement.setInt(1, orderId);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                setToItem(resultSet);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return itemList;
    }

    @Override
    public void addToItem(int prodId, int ordersId) {
        try (PreparedStatement pStatement = connection.prepareStatement(ITEM_CREATE)) {
            pStatement.setInt(1, prodId);
            pStatement.setInt(2, ordersId);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
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

    private void setToPStatement (Item item, String sqlCommand) {
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setInt(1, item.getProductId());
            pStatement.setInt(2, item.getOrderId());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

}

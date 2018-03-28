package kz.sushi.dao.impl;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.Orders;
import kz.sushi.dao.IOrders;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersImpl implements IOrders {
    private static Logger log = Logger.getLogger(OrdersImpl.class.getName());
    final static String ORDERS_CREATE = "INSERT INTO orders (user_id, cost, date_created) VALUES (?,?,?)";
    final static String ORDERS_UPDATE = "UPDATE orders SET user_id=?, cost=?, date_created=? WHERE id=?";
    final static String ORDERS_DELETE = "DELETE FROM orders";
    final static String ORDERS_FIND_ALL = "SELECT * FROM orders";
    private Connection connection;

    public OrdersImpl(Connection connection) {
        this.connection = connection;
    }

    List<Orders> ordersList = new ArrayList<>();

    @Override
    public void create(Orders orders) {
        setToPStatement(orders, ORDERS_CREATE);
    }

    @Override
    public void update(Orders orders) {
        setToPStatement(orders, ORDERS_UPDATE);
    }

    @Override
    public void delete(Orders orders) {
        try (PreparedStatement pStatement = connection.prepareStatement(ORDERS_DELETE)) {
            pStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public List<Orders> findAll() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ORDERS_FIND_ALL)) {
            setToOrder(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        return ordersList;
    }

    @Override
    public void addToOrders(int userId, int cost, java.util.Date dateCr) {
        try (PreparedStatement pStatement = connection.prepareStatement(ORDERS_CREATE)) {
            pStatement.setInt(1, userId);
            pStatement.setInt(2, cost);
            pStatement.setDate(3, new java.sql.Date(dateCr.getTime()));
            pStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    private void setToOrder(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Orders orders = new Orders();
            orders.setId(resultSet.getInt("id"));
            orders.setUserId(resultSet.getInt("user_id"));
            orders.setCost(resultSet.getInt("cost"));
            orders.setDateCreated(resultSet.getDate("date_created"));
            ordersList.add(orders);
        }
    }

    private void setToPStatement (Orders orders, String sqlCommand) {
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setInt(1, orders.getUserId());
            pStatement.setInt(2, orders.getCost());
            pStatement.setDate(3, new java.sql.Date(orders.getDateCreated().getTime()));
            pStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }
}

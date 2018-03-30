package kz.sushi.dao.impl;

import kz.sushi.dao.entity.Orders;
import kz.sushi.dao.IOrders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO implements IOrders {
    final static String ORDERS_CREATE = "INSERT INTO orders (user_id, cost, date_created) VALUES (?,?,?)";
    final static String ORDERS_UPDATE = "UPDATE orders SET user_id=?, cost=?, date_created=? WHERE id=?";
    final static String ORDERS_DELETE = "DELETE FROM orders";
    final static String ORDERS_FIND_ALL = "SELECT * FROM orders";
    private Connection connection;

    public OrdersDAO(Connection connection) {
        this.connection = connection;
    }

    List<Orders> ordersList = new ArrayList<>();

    @Override
    public void create(Orders orders) throws SQLException {
        setToPStatement(orders, ORDERS_CREATE);
    }

    @Override
    public void update(Orders orders) throws SQLException {
        setToPStatement(orders, ORDERS_UPDATE);
    }

    @Override
    public void delete(Orders orders) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(ORDERS_DELETE)) {
            pStatement.executeUpdate();
        }
    }

    @Override
    public List<Orders> findAll() throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ORDERS_FIND_ALL)) {
            setToOrder(resultSet);
        }
        return ordersList;
    }

    @Override
    public void addToOrders(int userId, int cost, java.util.Date dateCr) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(ORDERS_CREATE)) {
            pStatement.setInt(1, userId);
            pStatement.setInt(2, cost);
            pStatement.setDate(3, new java.sql.Date(dateCr.getTime()));
            pStatement.executeUpdate();
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

    private void setToPStatement (Orders orders, String sqlCommand) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setInt(1, orders.getUserId());
            pStatement.setInt(2, orders.getCost());
            pStatement.setDate(3, new java.sql.Date(orders.getDateCreated().getTime()));
            pStatement.executeUpdate();
        }
    }
}

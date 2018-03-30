package kz.sushi.service;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.Orders;
import kz.sushi.dao.entity.Product;
import kz.sushi.dao.impl.ItemDAO;
import kz.sushi.dao.impl.OrdersDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private static Logger log = Logger.getLogger(OrderService.class.getName());
    private List<Orders> ordersList;

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void createOrder (List<Product> productList, int userId, int totalCost) {
        ProductService productService = new ProductService();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ItemDAO itemDAO = new ItemDAO(connection);
            if (!productList.isEmpty()) {
                OrdersDAO ordersDAO = new OrdersDAO(connection);
                ordersDAO.addToOrders(userId, totalCost, new java.util.Date());
                log.trace("add to orders");

                List<Orders> ordersList = ordersDAO.findAll();
                Orders lastElem = ordersList.get(ordersList.size()-1);
                for (Product prod : productList) {
                    itemDAO.addToItem(prod.getId(), lastElem.getId());
                    log.trace("add product to item");
                }
                productList.clear();
                ordersList.clear();
                productService.setTotalCost(0);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public void showOrders() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            OrdersDAO ordersDAO = new OrdersDAO(connection);
            ordersList = ordersDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }
}

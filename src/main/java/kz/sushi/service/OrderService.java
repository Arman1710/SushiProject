package kz.sushi.service;

import kz.sushi.dao.entity.Orders;
import kz.sushi.dao.entity.Product;
import kz.sushi.dao.entity.User;
import kz.sushi.dao.impl.ItemDAO;
import kz.sushi.dao.impl.OrdersDAO;
import kz.sushi.dao.impl.UserDAO;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private static Logger log = Logger.getLogger(OrderService.class.getName());
    private List<Orders> ordersList;

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void createOrder (List<Product> productList, String login, int totalCost) {
        ProductService productService = new ProductService();
        try {
            ItemDAO itemDAO = new ItemDAO();
            if (!productList.isEmpty()) {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.findUserByLogin(login);
                OrdersDAO ordersDAO = new OrdersDAO();
                ordersDAO.addToOrders(user.getId(), totalCost, new java.util.Date());
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
        }
    }

    public void showOrders() {
        try {
            OrdersDAO ordersDAO = new OrdersDAO();
            ordersList = ordersDAO.findAll();
        } catch (SQLException e) {
            log.error(e);
        }
    }
}

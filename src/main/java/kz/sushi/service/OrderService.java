package kz.sushi.service;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.Orders;
import kz.sushi.dao.entity.Product;
import kz.sushi.dao.entity.User;
import kz.sushi.dao.impl.ItemImpl;
import kz.sushi.dao.impl.OrdersImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class OrderService {
    private static Logger log = Logger.getLogger(OrderService.class.getName());

    public String createOrder (HttpServletRequest request) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Product> productList = (List<Product>) session.getAttribute("productList");
        Locale locale = (Locale) session.getAttribute("locale");
        ResourceBundle bundle = ResourceBundle.getBundle("locale", locale);
        try {
            ItemImpl itemImpl = new ItemImpl(connection);
            if (!productList.isEmpty()) {
                int totalCost = (int) session.getAttribute("totalCost");
                OrdersImpl ordersImpl = new OrdersImpl(connection);
                ordersImpl.addToOrders(user.getId(), totalCost, new java.util.Date());

                log.trace("add to orders");

                List<Orders> ordersList = ordersImpl.findAll();
                Orders lastElem = ordersList.get(ordersList.size()-1);
                Iterator<Product> itProd = productList.iterator();
                while (itProd.hasNext()) {
                    Product prod = itProd.next();
                    itemImpl.addToItem(prod.getId(), lastElem.getId());
                    log.trace("add product to item");
                }
                productList.clear();
                ordersList.clear();
                session.removeAttribute("totalCost");

                if (session.getAttribute("success")==null) {
                    session.setAttribute("success", bundle.getString("basket.success"));
                }
                return "basket.jsp";
            } else {
                return "basket.jsp";
            }
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public String showOrders(HttpServletRequest request) {
        String page;
        Connection connection = ConnectionPool.getInstance().getConnection();
        HttpSession session = request.getSession();
        List<Orders> ordersList = (List<Orders>) session.getAttribute("ordersList");
        try {
            OrdersImpl ordersImpl = new OrdersImpl(connection);
            if (ordersList!=null) {
                ordersList.clear();
                session.removeAttribute("ordersList");
                page =  "admin-cabinet.jsp";
                log.trace("orderlist != null. remove attribute 'orderlist' from session");
            } else {
                ordersList = ordersImpl.findAll();
                session.setAttribute("ordersList", ordersList);
                page = "admin-cabinet.jsp";
                log.trace("show order list");
            }
            return page;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

    }

}

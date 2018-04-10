package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.dao.entity.Product;
import kz.sushi.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static kz.sushi.util.Constant.*;

public class CheckoutAction implements IBasicAction {
    private OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(LOGIN);
        List<Product> productList = (List<Product>) session.getAttribute(PRODUCT_LIST);
        int totalCost = (int) session.getAttribute(TOTAL_COST);

        orderService.createOrder(productList, login, totalCost);
        productList.clear();
        session.removeAttribute(TOTAL_COST);
        session.removeAttribute(PRODUCT_LIST);
        session.setAttribute(SUCCESS, BASKET_SUCCESS);
        return BASKET_PAGE;
    }
}



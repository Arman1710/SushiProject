package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static kz.sushi.util.Constant.*;

public class ShowAllOrdersAction implements IBasicAction {

    private OrderService orderService = new OrderService();
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(ORDERS_LIST)==null) {
            orderService.showOrders();
            session.setAttribute(ORDERS_LIST, orderService.getOrdersList());
        }
        return ADMIN_CABINET_PAGE;
    }

}

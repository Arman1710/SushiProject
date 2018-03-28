package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ShowAllOrders implements IBasicAction {
    private OrderService orderService = new OrderService();
    @Override
    public String execute(HttpServletRequest request) {
        return orderService.showOrders(request);
    }

}

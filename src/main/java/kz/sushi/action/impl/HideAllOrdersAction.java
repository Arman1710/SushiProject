package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.dao.entity.Orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static kz.sushi.util.Constant.*;

public class HideAllOrdersAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Orders> ordersList = (List<Orders>) session.getAttribute(ORDERS_LIST);
        if (ordersList != null) {
            ordersList.clear();
            session.removeAttribute(ORDERS_LIST);
        }
        return ADMIN_CABINET_PAGE;
    }
}

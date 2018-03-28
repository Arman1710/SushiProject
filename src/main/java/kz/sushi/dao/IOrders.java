package kz.sushi.dao;

import kz.sushi.dao.entity.Orders;

import java.util.Date;

public interface IOrders extends IBasic<Orders> {
    void addToOrders (int userId, int Cost, Date dateCr);
}

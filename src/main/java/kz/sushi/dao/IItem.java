package kz.sushi.dao;

import kz.sushi.dao.entity.Item;

import java.util.List;

public interface IItem extends IBasic<Item> {
    List<Item> findItemByOrderId(int orderId);
    void addToItem (int prodId, int ordersId);
}

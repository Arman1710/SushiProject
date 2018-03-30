package kz.sushi.dao;

import kz.sushi.dao.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface IItem extends IBasic<Item> {
    List<Item> findItemByOrderId(int orderId) throws SQLException;
    void addToItem (int prodId, int ordersId) throws SQLException;
}

package kz.sushi.dao;

import kz.sushi.dao.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProduct extends IBasic<Product> {
    List<Product> findByIdLocIdProdTypeId(String id, String locale, int type) throws SQLException;
    List<Product> findByLocIdAndProdTypeId (String locale ,int prodTypeId) throws SQLException;

}

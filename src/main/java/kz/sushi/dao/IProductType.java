package kz.sushi.dao;

import kz.sushi.dao.entity.ProductType;

import java.sql.SQLException;

public interface IProductType extends IBasic<ProductType> {
    ProductType findProdType (String type) throws SQLException;
}

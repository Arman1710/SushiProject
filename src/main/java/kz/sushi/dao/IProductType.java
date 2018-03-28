package kz.sushi.dao;

import kz.sushi.dao.entity.ProductType;

public interface IProductType extends IBasic<ProductType> {
    ProductType getProdType (String type);
}

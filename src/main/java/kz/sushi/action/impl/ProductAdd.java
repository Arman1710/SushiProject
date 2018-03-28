package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import javax.servlet.http.HttpServletRequest;
import kz.sushi.service.ProductService;

public class ProductAdd implements IBasicAction {
    private ProductService productService = new ProductService();
    @Override
    public String execute(HttpServletRequest request) {
        return productService.addProdToSession(request);
    }
}

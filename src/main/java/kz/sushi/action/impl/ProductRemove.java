package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class ProductRemove implements IBasicAction {
    private ProductService productService = new ProductService();
    @Override
    public String execute(HttpServletRequest request) {
        return productService.removeProdFromSession(request);
    }
}

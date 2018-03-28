package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kz.sushi.service.ProductService;

import java.util.Locale;

import static kz.sushi.util.Constant.*;


public class ProductAddAction implements IBasicAction {

    @Override
    public String execute(HttpServletRequest request) {
        ProductService productService = new ProductService();
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);

        String prodTypeId = request.getParameter(PRODUCT_TYPE);
        String prodId = request.getParameter(PRODUCT_ID);

        productService.addProdToSession(locale, prodId, prodTypeId);
        session.setAttribute(TOTAL_COST, productService.getTotalCost());
        session.setAttribute(PRODUCT_LIST, productService.getBasketProductList());

        return request.getParameter(CURRENT_PAGE);
    }
}

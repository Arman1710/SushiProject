package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.dao.entity.Product;
import kz.sushi.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static kz.sushi.util.Constant.*;

public class ProductRemoveAction implements IBasicAction {
    private ProductService productService = new ProductService();
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String prodId = request.getParameter(PRODUCT_ID);

        List<Product> productList = (List<Product>) session.getAttribute(PRODUCT_LIST);
        int totalCostInSession = (int) session.getAttribute(TOTAL_COST);
        productService.removeProdFromSession(productList, totalCostInSession, prodId);

        session.setAttribute(TOTAL_COST, productService.getReducedTotalCost());
        session.setAttribute(PRODUCT_LIST, productList);
        return BASKET_PAGE;
    }
}

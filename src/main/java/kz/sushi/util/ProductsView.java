package kz.sushi.util;

import kz.sushi.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ProductsView {

    public void addProdToSession (HttpSession session, Locale locale){
        ProductService productService = new ProductService();
        productService.allProductView(locale);
        session.setAttribute("rolls", productService.getAllRolls());
        session.setAttribute("sets", productService.getAllSets());
    }
}

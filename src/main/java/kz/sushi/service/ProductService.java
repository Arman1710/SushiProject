package kz.sushi.service;


import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.Product;
import kz.sushi.dao.entity.ProductType;
import kz.sushi.dao.impl.LocaleImpl;
import kz.sushi.dao.impl.ProductImpl;
import kz.sushi.dao.impl.ProductTypeImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ProductService {
    private static Logger log = Logger.getLogger(ProductService.class.getName());

    public String addProdToSession (HttpServletRequest request) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        HttpSession session = request.getSession();
        String prodTypeId = request.getParameter("productType");
        String prodId = request.getParameter("prodId");
        Locale locale = (Locale) session.getAttribute("locale");

        try {
            LocaleImpl localeImpl = new LocaleImpl(connection);
            kz.sushi.dao.entity.Locale localeNameDB = localeImpl.getLocaleByName(String.valueOf(locale));

            int totalCost = 0;
            ProductImpl rollImpl = ProductImpl.getProductImpl(connection);
            List<Product> productList = rollImpl.findByIdLocIdProdTypeId(prodId, String.valueOf(localeNameDB), Integer.parseInt(prodTypeId));
            Iterator<Product> itProd = productList.iterator();
            while (itProd.hasNext()) {
                Product prod = itProd.next();
                totalCost = totalCost + prod.getCost();
            }
            log.trace("Product add to session");
            session.setAttribute("totalCost", totalCost);
            session.setAttribute("productList", productList);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return request.getParameter("currentPage");
    }

    public String removeProdFromSession (HttpServletRequest request) {
        HttpSession session = request.getSession();
        String prodId = request.getParameter("prodId");

        List<Product> productList = (List<Product>) session.getAttribute("productList");
        int totalCost = (int) session.getAttribute("totalCost");
        Iterator<Product> itProd = productList.iterator();
        while (itProd.hasNext()) {
            Product p = itProd.next();
            if (p.getId() == Integer.parseInt(prodId)) {
                itProd.remove();
                totalCost = totalCost - p.getCost();
                break;
            }
        }
        log.trace("remove product from session");
        session.setAttribute("totalCost", totalCost);
        session.setAttribute("productList", productList);
        return "basket.jsp";
    }
}

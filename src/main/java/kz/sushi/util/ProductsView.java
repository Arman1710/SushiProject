package kz.sushi.util;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.impl.LocaleImpl;
import kz.sushi.dao.impl.ProductImpl;
import kz.sushi.dao.entity.Product;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.List;
import java.util.Locale;

public class ProductsView {
    public static final int PROD_TYPE_ROLL = 1;
    public static final int PROD_TYPE_SET = 2;

    public void addAllProd (HttpSession session) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ProductImpl productImpl = new ProductImpl(connection);
            Locale locale = (Locale) session.getAttribute("locale");
            LocaleImpl localeImpl = new LocaleImpl(connection);
            kz.sushi.dao.entity.Locale localeNameDB = localeImpl.getLocaleByName(String.valueOf(locale));

            List<Product> allRolls = productImpl.findByLocIdAndProdTypeId(String.valueOf(localeNameDB), PROD_TYPE_ROLL);
            session.setAttribute("rolls", allRolls);
            List<Product> allSets = productImpl.findByLocIdAndProdTypeId(String.valueOf(localeNameDB), PROD_TYPE_SET);
            session.setAttribute("sets", allSets);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

    }
}

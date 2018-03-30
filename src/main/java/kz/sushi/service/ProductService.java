package kz.sushi.service;


import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.Product;
import kz.sushi.dao.impl.LocaleDAO;
import kz.sushi.dao.impl.ProductDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ProductService {
    private static final int PROD_TYPE_ROLL = 1;
    private static final int PROD_TYPE_SET = 2;
    private static Logger log = Logger.getLogger(ProductService.class.getName());
    private int totalCost = 0;
    private int baskProdCost = 0;
    private int reducedTotalCost;
    private List<Product> basketProductList;
    private List<Product> allRolls;
    private List<Product> allSets;

    public List<Product> getAllRolls() {
        return allRolls;
    }

    public List<Product> getAllSets() {
        return allSets;
    }

    public int getReducedTotalCost() {
        return reducedTotalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public List<Product> getBasketProductList() {
        return basketProductList;
    }

    public void addProdToSession (Locale locale, String prodId, String prodTypeId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            LocaleDAO localeDAO = new LocaleDAO(connection);
            kz.sushi.dao.entity.Locale localeNameDB = localeDAO.getLocaleByName(String.valueOf(locale));
            ProductDAO rollImpl = ProductDAO.getProductDAO(connection);
            basketProductList = rollImpl.findByIdLocIdProdTypeId(prodId, String.valueOf(localeNameDB), Integer.parseInt(prodTypeId));
            for (Product prod : basketProductList) {
                totalCost = totalCost + prod.getCost();
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public void removeProdFromSession (List<Product> productList, int totalCostInSession, String prodId) {

        Iterator<Product> itProd = productList.iterator();
        while (itProd.hasNext()) {
            Product p = itProd.next();
            if (p.getId() == Integer.parseInt(prodId)) {
                itProd.remove();
                reducedTotalCost = totalCostInSession - p.getCost();
                break;
            }
        }
        log.trace("remove product from session");
    }

    public void allProductView (Locale locale){

        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ProductDAO productDAO = new ProductDAO(connection);

            LocaleDAO localeDAO = new LocaleDAO(connection);
            kz.sushi.dao.entity.Locale localeNameDB = localeDAO.getLocaleByName(String.valueOf(locale));
            allRolls = productDAO.findByLocIdAndProdTypeId(String.valueOf(localeNameDB), PROD_TYPE_ROLL);
            allSets = productDAO.findByLocIdAndProdTypeId(String.valueOf(localeNameDB), PROD_TYPE_SET);

        } catch (SQLException e) {
            log.error(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }
}

package kz.sushi.action.impl;

import kz.sushi.dao.entity.Product;
import kz.sushi.action.IBasicAction;
import kz.sushi.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static kz.sushi.util.Constant.*;

public class LogoutAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Product> productList = (List<Product>) session.getAttribute(PRODUCT_LIST);
        if (productList!=null) {
            productList.clear();
            session.removeAttribute(PRODUCT_LIST);
        }

        session.removeAttribute(TOTAL_COST);
        session.removeAttribute(USER_ID);
        session.removeAttribute(LOGIN);
        session.removeAttribute(SUCCESS);

        return INDEX_PAGE;
    }
}

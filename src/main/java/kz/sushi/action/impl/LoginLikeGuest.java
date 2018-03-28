package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.util.ProductsView;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class LoginLikeGuest implements IBasicAction {
    private static Logger log = Logger.getLogger(LoginLikeGuest.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        HttpSession session = request.getSession();

        try {
            ProductsView allProd = new ProductsView();
            allProd.addAllProd(session);
            log.trace("add Rolls and Sets to session");
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return "index.jsp";
    }
}

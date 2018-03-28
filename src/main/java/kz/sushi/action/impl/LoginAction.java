package kz.sushi.action.impl;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.impl.UserImpl;
import kz.sushi.dao.entity.User;
import kz.sushi.action.IBasicAction;
import kz.sushi.util.ProductsView;
import kz.sushi.util.PswHash;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Objects;

public class LoginAction implements IBasicAction {
    private static Logger log = Logger.getLogger(LoginAction.class.getName());
    private String page="";
    @Override
    public String execute(HttpServletRequest request) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        HttpSession session = request.getSession();
        PswHash pswHash = new PswHash();
        ProductsView allProd = new ProductsView();
        allProd.addAllProd(session);

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            UserImpl userImpl = new UserImpl(connection);
            User user = userImpl.findByLoginAndPsw(login, pswHash.md5Hash(password));
            String userRole = String.valueOf(user.getRole());

            if (Objects.equals(userRole, "ADMIN")) {
                session.removeAttribute("loginError");
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());
                page = "admin-cabinet.jsp";
                log.trace("Role is ADMIN");
            }
            if (Objects.equals(userRole, "USER")) {
                session.removeAttribute("loginError");
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());
                page = "user-index.jsp";
                log.trace("Role is USER");
            }

            if (Objects.equals(userRole, "null")) {
                session.setAttribute("loginError", "Unknown login or password, try again!");
                page = "login.jsp";
                log.trace("Login error. Role is null");
            }
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return page;
    }
}

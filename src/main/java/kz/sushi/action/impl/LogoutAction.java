package kz.sushi.action.impl;

import kz.sushi.dao.entity.Product;
import kz.sushi.action.IBasicAction;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LogoutAction implements IBasicAction {
    private static Logger log = Logger.getLogger(LogoutAction.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("productList")!=null) {
            List<Product> productList = (List<Product>) session.getAttribute("productList");
            productList.clear();
            session.removeAttribute("productList");
            session.removeAttribute("totalCost");
            log.trace("attributes from session are removed");
        }
        session.removeAttribute("role");
        session.removeAttribute("user");
        session.removeAttribute("success");

        return "index.jsp";
    }
}

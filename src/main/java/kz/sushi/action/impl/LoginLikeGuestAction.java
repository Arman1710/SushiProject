package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.util.ProductsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static kz.sushi.util.Constant.*;

public class LoginLikeGuestAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);
        ProductsView productsView = new ProductsView();
        productsView.addProdToSession(session,locale);
        return INDEX_PAGE;
    }
}

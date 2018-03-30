package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.service.UserService;
import kz.sushi.util.ProductsView;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static kz.sushi.util.Constant.*;

public class LoginAction implements IBasicAction {
    private static Logger log = Logger.getLogger(LoginAction.class.getName());
    private String page = "";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);

        ProductsView productsView = new ProductsView();
        productsView.addProdToSession(session, locale);

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        UserService userService = new UserService();
        userService.logIn(login, password);
        int userRoleId = userService.getUserRoleId();
        int userId = userService.getUserId();
        String userlogin = userService.getUserlogin();

        if (userRoleId == ADMIN_ROLE) {
            session.removeAttribute(LOGIN_ERROR);
            session.setAttribute(LOGIN, userlogin);
            session.setAttribute(USER_ID, userId);
            page = ADMIN_CABINET_PAGE;
            log.trace("Role is ADMIN");
        }
        if (userRoleId == USER_ROLE) {
            session.removeAttribute(LOGIN_ERROR);
            session.setAttribute(LOGIN, userlogin);
            session.setAttribute(USER_ID, userId);
            page = USER_INDEX_PAGE;
            log.trace("Role is USER");
        }

        if (userRoleId == 0) {
            session.setAttribute(LOGIN_ERROR, LOGIN_ERROR);
            page = LOGIN_PAGE;
            log.trace("Login error. Role is null");
        }
        return page;
    }
}

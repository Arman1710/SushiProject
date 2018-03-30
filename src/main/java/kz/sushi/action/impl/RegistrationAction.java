package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.exception.WrongInputDataException;
import kz.sushi.service.UserService;
import kz.sushi.util.ProductsView;
import kz.sushi.util.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static kz.sushi.util.Constant.*;
import static kz.sushi.util.Validator.getErrorMsg;

public class RegistrationAction implements IBasicAction {
    private static Logger log = Logger.getLogger(RegistrationAction.class.getName());
    private UserService userService = new UserService();
    String page = REGISTRATION_PAGE;
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String email = request.getParameter(EMAIL);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String phone = request.getParameter(PHONE);
        String address = request.getParameter(ADDRESS);
        String birthday = request.getParameter(BIRTHDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdayDate = null;
        try {
            if (!birthday.isEmpty()) {
                birthdayDate = sdf.parse(birthday);
            }
        } catch (ParseException e) {
            log.error(e);
        }
        Locale locale = (Locale) session.getAttribute(LOCALE);
        ProductsView productsView = new ProductsView();
        productsView.addProdToSession(session,locale);

        try {
            if (userService.createUser(login, password, email, address, phone, birthdayDate)) {
                session.setAttribute(LOGIN, login);
                session.setAttribute(USER_ID, userService.getUserId());
                session.removeAttribute(ERROR_MESSAGE);
                page = USER_INDEX_PAGE;
            } else {
                session.setAttribute(ERROR_MESSAGE, getErrorMsg());
            }
        } catch (WrongInputDataException e) {
            log.error(e);
        }


        return page;
    }
}

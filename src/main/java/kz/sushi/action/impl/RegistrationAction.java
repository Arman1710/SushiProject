package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.dao.entity.User;
import kz.sushi.exception.WrongInputDataException;
import kz.sushi.service.UserService;
import kz.sushi.util.ProductsView;
import kz.sushi.util.PswHash;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static kz.sushi.util.Constant.*;
import static kz.sushi.util.Validator.getErrorMsg;

public class RegistrationAction implements IBasicAction {
    private static Logger log = Logger.getLogger(RegistrationAction.class.getName());

    String page;
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


        UserService userService = new UserService();
        try {
            User user = new User();
            PswHash pswHash = new PswHash();
            user.setLogin(login);
            user.setPassword(pswHash.md5Hash(password));
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(address);
            user.setBirthday(birthdayDate);
            user.setUser_role_id(USER_ROLE);
            if (userService.createUser(user)) {
                session.setAttribute(LOGIN, login);
                session.removeAttribute(ERROR_MESSAGE);
                page = USER_INDEX_PAGE;
            }
        } catch (WrongInputDataException e) {
            log.error(e);
            session.setAttribute(ERROR_MESSAGE, getErrorMsg());
            page = REGISTRATION_PAGE;
        }

        return page;
    }
}

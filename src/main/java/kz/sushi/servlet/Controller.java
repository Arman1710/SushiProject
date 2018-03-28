package kz.sushi.servlet;

import kz.sushi.action.IBasicAction;
import kz.sushi.action.impl.*;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class Controller extends HttpServlet  {
    private static Logger log = Logger.getLogger(Controller.class.getName());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        String parameter = request.getParameter("action");
        IBasicAction action = null;
        switch (parameter) {
            case "changeLocale": action = new LocaleAction();
            break;
            case "productAdd": action = new ProductAdd();
            break;
            case "productRemove": action = new ProductRemove();
            break;
            case "registration" : action = new RegistrationAction();
            break;
            case "logout" : action = new LogoutAction();
            break;
            case "login" : action = new LoginAction();
            break;
            case "checkout" : action = new Checkout();
            break;
            case "showAllOrders" : action = new ShowAllOrders();
            break;
            case "loginLikeGuest" : action = new LoginLikeGuest();
            break;
        }
        String path = action.execute(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

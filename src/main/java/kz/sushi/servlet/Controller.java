package kz.sushi.servlet;

import kz.sushi.action.ActionFactory;
import kz.sushi.action.IBasicAction;
import kz.sushi.action.impl.*;

import kz.sushi.dao.IBasic;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;


public class Controller extends HttpServlet  {
    private static Logger log = Logger.getLogger(Controller.class.getName());
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        String parameter = request.getParameter("action");
        Map<String, IBasicAction> actionMap = ActionFactory.actionMap;
        IBasicAction action = actionMap.get(parameter);
        String path = action.execute(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

}

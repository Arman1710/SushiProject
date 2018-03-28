package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LocaleAction implements IBasicAction {
    private static Logger log = Logger.getLogger(Locale.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter("locale");
        switch (locale) {
            case "ru":
                session.setAttribute("locale", new Locale("ru", "KZ"));
                log.trace("locale is ru_KZ");
                break;
            case "en":
                session.setAttribute("locale", new Locale("en", "US"));
                log.trace("locale is en_US");
                break;
        }
        return request.getParameter("currentPage");
    }
}

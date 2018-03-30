package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static kz.sushi.util.Constant.*;

public class LocaleAction implements IBasicAction {
    private static Logger log = Logger.getLogger(Locale.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(LOCALE);
        switch (locale) {
            case "ru":
                session.setAttribute(LOCALE, new Locale("ru", "KZ"));
                log.trace("locale is ru_KZ");
                break;
            case "en":
                session.setAttribute(LOCALE, new Locale("en", "US"));
                log.trace("locale is en_US");
                break;
        }
        return request.getParameter(CURRENT_PAGE);
    }
}

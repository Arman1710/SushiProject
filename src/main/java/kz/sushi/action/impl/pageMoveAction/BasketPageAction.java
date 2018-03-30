package kz.sushi.action.impl.pageMoveAction;

import kz.sushi.action.IBasicAction;

import javax.servlet.http.HttpServletRequest;

import static kz.sushi.util.Constant.BASKET_PAGE;

public class BasketPageAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        return BASKET_PAGE;
    }
}

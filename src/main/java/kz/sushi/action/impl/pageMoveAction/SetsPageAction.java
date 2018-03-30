package kz.sushi.action.impl.pageMoveAction;

import kz.sushi.action.IBasicAction;

import javax.servlet.http.HttpServletRequest;

import static kz.sushi.util.Constant.SETS_PAGE;

public class SetsPageAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        return SETS_PAGE;
    }
}

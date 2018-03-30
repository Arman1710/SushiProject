package kz.sushi.action.impl.pageMoveAction;

import kz.sushi.action.IBasicAction;

import javax.servlet.http.HttpServletRequest;

import static kz.sushi.util.Constant.ROLLS_PAGE;

public class RollsPageAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        return ROLLS_PAGE;
    }
}

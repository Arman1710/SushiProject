package kz.sushi.action.impl.pageMoveAction;

import kz.sushi.action.IBasicAction;

import javax.servlet.http.HttpServletRequest;

import static kz.sushi.util.Constant.USER_ROLLS_PAGE;

public class UserRollsPageAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        return USER_ROLLS_PAGE;
    }
}

package kz.sushi.action.impl.pageMoveAction;

import kz.sushi.action.IBasicAction;

import javax.servlet.http.HttpServletRequest;

import static kz.sushi.util.Constant.USER_SETS_PAGE;

public class UserSetsPageAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        return USER_SETS_PAGE;
    }
}

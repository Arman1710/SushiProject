package kz.sushi.action.impl.pageMoveAction;

import kz.sushi.action.IBasicAction;

import javax.servlet.http.HttpServletRequest;

import static kz.sushi.util.Constant.LOGIN_PAGE;

public class LoginPageAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        return LOGIN_PAGE;
    }
}

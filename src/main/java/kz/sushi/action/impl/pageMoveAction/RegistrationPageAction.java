package kz.sushi.action.impl.pageMoveAction;

import kz.sushi.action.IBasicAction;

import javax.servlet.http.HttpServletRequest;

import static kz.sushi.util.Constant.REGISTRATION_PAGE;

public class RegistrationPageAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        return REGISTRATION_PAGE;
    }
}

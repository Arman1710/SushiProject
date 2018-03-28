package kz.sushi.action.impl;

import kz.sushi.action.IBasicAction;
import kz.sushi.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationAction implements IBasicAction {
    private UserService userService = new UserService();


    @Override
    public String execute(HttpServletRequest request) {
        return userService.createUser(request);
    }
}

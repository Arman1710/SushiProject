package kz.sushi.dao;

import kz.sushi.dao.entity.User;

public interface IUser extends IBasic<User> {
    User findByLoginAndPsw (String login, String password);
}

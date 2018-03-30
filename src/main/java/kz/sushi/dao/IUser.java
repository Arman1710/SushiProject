package kz.sushi.dao;

import kz.sushi.dao.entity.User;

import java.sql.SQLException;

public interface IUser extends IBasic<User> {
    User findByLoginAndPsw (String login, String password) throws SQLException;
    User findUserByLogin (String login) throws SQLException;
}

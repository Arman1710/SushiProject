package kz.sushi.service;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.User;
import kz.sushi.dao.impl.UserDAO;
import kz.sushi.exception.WrongInputDataException;
import kz.sushi.util.PswHash;
import kz.sushi.util.Validator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static kz.sushi.util.Constant.USER_ROLE;

public class UserService {
    private static Logger log = Logger.getLogger(UserService.class.getName());
    private int userRoleId;
    private int userId;
    private String userlogin;

    public int getUserRoleId() {
        return userRoleId;
    }

    public String getUserlogin() {
        return userlogin;
    }

    public int getUserId() {
        return userId;
    }

    public boolean createUser (String login, String password, String email, String address, String phone, Date birthdayDate) throws WrongInputDataException {

        Connection connection = ConnectionPool.getInstance().getConnection();
        Validator validator = new Validator();
        try {
            if (validator.checkInputData(login, password, email, address, phone)) {
                UserDAO userDAO = new UserDAO(connection);
                User user = new User();
                PswHash pswHash = new PswHash();
                user.setLogin(login);
                user.setPassword(pswHash.md5Hash(password));
                user.setEmail(email);
                user.setPhone(phone);
                user.setAddress(address);
                user.setBirthday(birthdayDate);
                user.setUser_role_id(USER_ROLE);
                userDAO.create(user);
                userId = user.getId();
                log.trace("user " + user.getLogin() + " is created");
            }
        } catch (SQLException e) {
            log.error(e);
        } finally{
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return true;
    }

    public void logIn(String login, String password) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PswHash pswHash = new PswHash();
        try {
            UserDAO userDAO = new UserDAO(connection);
            User user = userDAO.findByLoginAndPsw(login, pswHash.md5Hash(password));
            userRoleId = user.getUser_role_id();
            userlogin = user.getLogin();
            userId = user.getId();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }
}

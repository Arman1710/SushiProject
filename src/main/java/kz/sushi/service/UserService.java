package kz.sushi.service;

import kz.sushi.dao.entity.User;
import kz.sushi.dao.impl.UserDAO;
import kz.sushi.exception.WrongInputDataException;
import kz.sushi.util.PswHash;
import kz.sushi.util.Validator;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class UserService {
    private static Logger log = Logger.getLogger(UserService.class.getName());
    private int userRoleId;
    private String userlogin;

    public int getUserRoleId() {
        return userRoleId;
    }

    public String getUserlogin() {
        return userlogin;
    }

    public boolean createUser (User user) throws WrongInputDataException {
        Validator validator = new Validator();
        boolean isCreated =false;
        try {
            if (validator.checkInputData(user)) {
                UserDAO userDAO = new UserDAO();
                userDAO.create(user);
                log.trace("user " + user.getLogin() + " is created");
                isCreated = true;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return isCreated;
    }

    public void logIn(String login, String password) {
        PswHash pswHash = new PswHash();
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findByLoginAndPsw(login, pswHash.md5Hash(password));
            userRoleId = user.getUser_role_id();
            userlogin = user.getLogin();
        } catch (SQLException e) {
            log.error(e);
        }
    }
}

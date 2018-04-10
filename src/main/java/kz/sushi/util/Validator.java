package kz.sushi.util;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.User;
import kz.sushi.dao.impl.UserDAO;
import kz.sushi.exception.WrongInputDataException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_PATTERN = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private static String errorMsg;

    public static String getErrorMsg() {
        return errorMsg;
    }

    public static void setErrorMsg(String errorMsg) {
        Validator.errorMsg = errorMsg;
    }

    public boolean checkInputData (User user) throws WrongInputDataException, SQLException {
        errorMsg = null;

        if (user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getAddress().isEmpty() || user.getPhone().isEmpty()) {
             setErrorMsg("emptyFieldsError");
            throw new WrongInputDataException("empty fields");
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            errorMsg = "notCorrectEmail";
            throw new WrongInputDataException("notCorrectEmail");
        }

        if (!isNumeric(user.getPhone())) {
            errorMsg = "isNumericError";
            throw new WrongInputDataException("phone is not numeric");
        }

        if (userIsExists(user.getLogin())) {
            errorMsg = "userExistsError";
            throw new WrongInputDataException("user is already exists");
        }
        return isValid();
    }

    private boolean isValid() {
        return errorMsg == null;
    }

    private boolean userIsExists(String login) throws SQLException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByLogin(login);
        return (Objects.equals(user.getLogin(), login));
    }

    private boolean isNumeric (String phone) {
        Pattern p = Pattern.compile(PHONE_PATTERN);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}

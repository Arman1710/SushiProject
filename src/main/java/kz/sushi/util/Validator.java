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

    public boolean checkInputData (String login, String password, String email, String address, String phone) throws WrongInputDataException, SQLException {

        if (login.isEmpty() || password.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            errorMsg = "emptyFieldsError";
            throw new WrongInputDataException("empty fields");
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errorMsg = "notCorrectEmail";
            throw new WrongInputDataException("notCorrectEmail");
        }

        if (!isNumeric(phone)) {
            errorMsg = "isNumericError";
            throw new WrongInputDataException("phone is not numeric");
        }

        if (userIsExists(login)) {
            errorMsg = "userExistsError";
            throw new WrongInputDataException("user is already exists");
        }
        return isValid();
    }

    public boolean isValid () {
        return errorMsg==null;
    }

    private boolean userIsExists(String login) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            UserDAO userDAO = new UserDAO(connection);
            User user = userDAO.findUserByLogin(login);
            return (Objects.equals(user.getLogin(), login));
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    private boolean isNumeric (String phone) {
        Pattern p = Pattern.compile(PHONE_PATTERN);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}

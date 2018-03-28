package kz.sushi.service;

import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.User;
import kz.sushi.dao.impl.UserImpl;
import kz.sushi.exception.WrongInputDataException;
import kz.sushi.util.PswHash;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_PATTERN = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private static Logger log = Logger.getLogger(UserService.class.getName());

    public String createUser (HttpServletRequest request) {
        HttpSession session = request.getSession();
        Connection connection = ConnectionPool.getInstance().getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        Date birthdayDate = null;
        try {
            if (!birthday.isEmpty()) {
                birthdayDate = sdf.parse(birthday);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        Locale locale = (Locale) session.getAttribute("locale");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale", locale);
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        try {
            if (email.isEmpty()) {
                session.setAttribute("emailError", resourceBundle.getString("registration.emptyEmail"));
                throw new WrongInputDataException("empty Email");
            } else {
                session.removeAttribute("emailError");
                try {
                    if (!matcher.matches()) {
                        session.setAttribute("emailError", resourceBundle.getString("registration.notCorrectEmail"));
                        throw new WrongInputDataException("not correct email");
                    } else {
                        session.removeAttribute("emailError");
                    }
                } catch (WrongInputDataException e) {
                    log.error(e);
                }
            }
        } catch (WrongInputDataException e) {
            log.error(e);
        }

        try {
            if (login.isEmpty()) {
                session.setAttribute("loginRegError", resourceBundle.getString("registration.emptyLogin"));
                throw new WrongInputDataException("empty login");
            } else {
                session.removeAttribute("loginRegError");
            }
        } catch (WrongInputDataException e) {
            log.error(e);
        }



        try {
            if (userIsExists(login)) {
                session.setAttribute("loginError", resourceBundle.getString("registration.userExists"));
                throw new WrongInputDataException("user is already exists");
            } else {
                session.removeAttribute("loginError");
            }
        } catch (WrongInputDataException e) {
            log.error(e);
        }



        try {
            if (password.isEmpty()) {
                session.setAttribute("passwordError", resourceBundle.getString("registration.emptyPassword"));
                throw new WrongInputDataException("empty password");
            } else {
                session.removeAttribute("passwordError");
            }
        } catch (WrongInputDataException e) {
            log.error(e);
        }



        try {
            if (!isNumeric(phone)) {
                session.setAttribute("phoneError", resourceBundle.getString("registration.isNumeric"));
                throw new WrongInputDataException("phone is not numeric");
            } else {
                session.removeAttribute("phoneError");
            }
        } catch (WrongInputDataException e) {
            log.error(e);
        }



        try {
            if (phone.isEmpty()) {
                session.setAttribute("phoneError", resourceBundle.getString("registration.emptyPhone"));
                throw new WrongInputDataException("empty phone");
            } else {
                session.removeAttribute("phoneError");
            }
        } catch (WrongInputDataException e) {
            log.error(e);
        }



        try {
            if (address.isEmpty()) {
                session.setAttribute("addressError", resourceBundle.getString("registration.emptyAddress"));
                throw new WrongInputDataException("empty address");
            } else {
                session.removeAttribute("addressError");
            }
        } catch (WrongInputDataException e) {
            log.error(e);
        }



        try {
            if (birthday.isEmpty()) {
                session.setAttribute("birthdayError", resourceBundle.getString("registration.emptyBirthday"));
            } else {
                session.removeAttribute("birthdayError");
            }
            throw new WrongInputDataException("empty birthday");
        } catch (WrongInputDataException e) {
            log.error(e);
        }

        try {
            if (!(userIsExists(login)) && !login.isEmpty() && !password.isEmpty() && !address.isEmpty() && !phone.isEmpty() && isNumeric(phone) && matcher.matches()) {
                UserImpl userImpl = new UserImpl(connection);
                User user = new User();
                PswHash pswHash = new PswHash();
                user.setLogin(login);
                user.setEmail(email);
                user.setPhone(phone);
                user.setAddress(address);
                user.setBirthday(birthdayDate);
                user.setRole(User.Role.USER);
                user.setPassword(pswHash.md5Hash(password));
                userImpl.create(user);
                User.Role role = user.getRole();
                session.setAttribute("user", user);
                session.setAttribute("role", role);
                return  "user-index.jsp";
            } else {
                return "registration.jsp";
            }
        } finally{
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    private boolean userIsExists(String login) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean userExists = false;
        try {
            UserImpl userImpl = new UserImpl(connection);
            List<User> userList = userImpl.findAll();
            Iterator<User> userIterator = userList.iterator();
            while (userIterator.hasNext()){
                User itUser = userIterator.next();
                if (itUser.getLogin().equals(login)) {
                    userExists = true;
                    break;
                }
                else {
                    userExists = false;
                }
            }
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return userExists;
    }


    private  boolean isNumeric (String phone) {
        Pattern p = Pattern.compile(PHONE_PATTERN);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}

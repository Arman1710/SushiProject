package kz.sushi.dao.impl;

import kz.sushi.dao.entity.User;
import kz.sushi.dao.IUser;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements IUser {
    private static Logger log = Logger.getLogger(UserImpl.class.getName());
    final static String USER_CREATE = "INSERT INTO user (login, email, address, phone_number, birthday, role, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final static String USER_UPDATE = "UPDATE user  SET name=?, email=?, address=?, phone_number=?, birthday=?, role=?, password=? WHERE id=?";
    final static String USER_DELETE = "DELETE FROM user";
    final static String USER_FIND_ALL = "SELECT * FROM user";
    final static String USER_FIND_BY_LOGIN_PSW = "SELECT * FROM user  WHERE login=? AND password=?";

    private Connection connection;

    public UserImpl(Connection connection) {
        this.connection = connection;
    }



    List<User> userList = new ArrayList<>();

    @Override
    public void create(User user) {
        setToPStatement(user, USER_CREATE);
    }

    @Override
    public void update(User user) {
        setToPStatement(user, USER_UPDATE);
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement pStatement = connection.prepareStatement(USER_DELETE)) {
            pStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(USER_FIND_ALL)) {
            while (resultSet.next()) {
                setToUser(resultSet);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return userList;
    }


    @Override
    public User findByLoginAndPsw(String login, String password) {
        User user = new User();
        try (PreparedStatement pStatement = connection.prepareStatement(USER_FIND_BY_LOGIN_PSW)) {
            pStatement.setString(1, login);
            pStatement.setString(2, password);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAddress(resultSet.getString("address"));
                    user.setPhone(resultSet.getString("phone_number"));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setRole(User.Role.valueOf(resultSet.getString("role")));
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }

        return user;
    }

    private void setToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setAddress(resultSet.getString("address"));
        user.setPhone(resultSet.getString("phone_number"));
        user.setBirthday(resultSet.getDate("birthday"));
        user.setRole(User.Role.valueOf(resultSet.getString("role")));
        userList.add(user);
    }

    private void setToPStatement (User user, String sqlCommand) {
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setString(1, user.getLogin());
            pStatement.setString(2, user.getEmail());
            pStatement.setString(3, user.getAddress());
            pStatement.setString(4, user.getPhone());
            pStatement.setDate(5, new java.sql.Date(user.getBirthday().getTime()));
            pStatement.setString(6, String.valueOf(User.Role.valueOf(String.valueOf(user.getRole()))));
            pStatement.setString(7, user.getPassword());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }
}

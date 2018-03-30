package kz.sushi.dao.impl;

import kz.sushi.dao.entity.User;
import kz.sushi.dao.IUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUser {
    final static String USER_CREATE = "INSERT INTO user (logIn, email, address, phone_number, birthday, user_role_id, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final static String USER_UPDATE = "UPDATE user  SET name=?, email=?, address=?, phone_number=?, birthday=?, user_role_id=?, password=? WHERE id=?";
    final static String USER_DELETE = "DELETE FROM user";
    final static String USER_FIND_ALL = "SELECT * FROM user";
    final static String USER_FIND_BY_LOGIN_PSW = "SELECT * FROM user  WHERE login=? AND password=?";
    final static String USER_FIND_BY_LOGIN = "SELECT * FROM user WHERE login=?";

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    List<User> userList = new ArrayList<>();

    @Override
    public void create(User user) throws SQLException {
        setToPStatement(user, USER_CREATE);
    }

    @Override
    public void update(User user) throws SQLException {
        setToPStatement(user, USER_UPDATE);
    }

    @Override
    public void delete(User user) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(USER_DELETE)) {
            pStatement.executeUpdate();
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        User user = new User();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(USER_FIND_ALL)) {
            while (resultSet.next()) {
                setToUser(resultSet, user);
                userList.add(user);
            }
        }
        return userList;
    }


    @Override
    public User findByLoginAndPsw(String login, String password) throws SQLException {
        User user = new User();
        try (PreparedStatement pStatement = connection.prepareStatement(USER_FIND_BY_LOGIN_PSW)) {
            pStatement.setString(1, login);
            pStatement.setString(2, password);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    setToUser(resultSet, user);
                }
            }
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) throws SQLException {
        User user = new User();
        try (PreparedStatement pStatement = connection.prepareStatement(USER_FIND_BY_LOGIN)) {
            pStatement.setString(1, login);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    setToUser(resultSet, user);
                }
            }
            return user;
        }
    }

    private void setToUser(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("logIn"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setAddress(resultSet.getString("address"));
        user.setPhone(resultSet.getString("phone_number"));
        user.setBirthday(resultSet.getDate("birthday"));
        user.setUser_role_id(resultSet.getInt("user_role_id"));
    }

    private void setToPStatement (User user, String sqlCommand) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setString(1, user.getLogin());
            pStatement.setString(2, user.getEmail());
            pStatement.setString(3, user.getAddress());
            pStatement.setString(4, user.getPhone());
            pStatement.setDate(5, new java.sql.Date(user.getBirthday().getTime()));
            pStatement.setInt(6, user.getUser_role_id());
            pStatement.setString(7, user.getPassword());
            pStatement.executeUpdate();
        }
    }
}

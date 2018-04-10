package kz.sushi.dao.impl;

import kz.sushi.dao.IUserRole;
import kz.sushi.dao.connectionPool.ConnectionPool;
import kz.sushi.dao.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAO implements IUserRole {
    private final static String USER_ROLE_CREATE = "INSERT INTO user_role (role, id) VALUES (?, ?)";
    private final static String USER_ROLE_UPDATE = "UPDATE user_role SET type=? WHERE id=?";
    private final static String USER_ROLE_DELETE = "DELETE FROM user_role";
    private final static String USER_ROLE_FIND_ALL = "SELECT * FROM user_role";
    private final static String USER_ROLE_FIND_USER_ROLE = "SELECT * FROM user_role WHERE role=?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private UserRole userRole = new UserRole();
    private List<UserRole> userRoleList = new ArrayList<>();

    @Override
    public void create(UserRole userRole) throws SQLException {
        setToPStatement(userRole, USER_ROLE_CREATE);
    }

    @Override
    public void update(UserRole userRole) throws SQLException {
        setToPStatement(userRole, USER_ROLE_UPDATE);
    }

    @Override
    public void delete(UserRole userRole) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(USER_ROLE_DELETE)) {
            pStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<UserRole> findAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(USER_ROLE_FIND_ALL)) {
            setToUserRole(resultSet);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return userRoleList;
    }

    @Override
    public UserRole findUserRole(String role) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(USER_ROLE_FIND_USER_ROLE)) {
            pStatement.setString(1, role);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    userRole.setId(resultSet.getInt("id"));
                }
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return userRole;
    }

    private void setToUserRole(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            userRole =new UserRole();
            userRole.setId(resultSet.getInt("id"));
            userRole.setRole(resultSet.getString("role"));
            userRoleList.add(userRole);
        }
    }
    private void setToPStatement (UserRole userRole, String sqlCommand) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setString(1, userRole.getRole());
            pStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}

package kz.sushi.dao.impl;

import kz.sushi.dao.IUserRole;
import kz.sushi.dao.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRoleDAO implements IUserRole {
    final static String USER_ROLE_CREATE = "INSERT INTO user_role (role, id) VALUES (?, ?)";
    final static String USER_ROLE_UPDATE = "UPDATE user_role SET type=? WHERE id=?";
    final static String USER_ROLE_DELETE = "DELETE FROM user_role";
    final static String USER_ROLE_FIND_ALL = "SELECT * FROM user_role";
    final static String USER_ROLE_FIND_USER_ROLE = "SELECT * FROM user_role WHERE role=?";

    private Connection connection;

    public UserRoleDAO (Connection connection) {
        this.connection = connection;
    }
    UserRole userRole = new UserRole();

    @Override
    public UserRole findUserRole(String role) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(USER_ROLE_FIND_USER_ROLE)) {
            pStatement.setString(1, role);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    userRole.setId(resultSet.getInt("id"));
                }
            }
        }
        return userRole;
    }

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

    }

    @Override
    public List<UserRole> findAll() throws SQLException {
        return null;
    }

    private void setToPStatement (UserRole userRole, String sqlCommand) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setString(1, userRole.getRole());
            pStatement.executeUpdate();
        }
    }
}

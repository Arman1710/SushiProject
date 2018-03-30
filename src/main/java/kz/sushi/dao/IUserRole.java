package kz.sushi.dao;

import kz.sushi.dao.entity.UserRole;

import java.sql.SQLException;

public interface IUserRole extends IBasic<UserRole> {
    UserRole findUserRole (String role) throws SQLException;
}

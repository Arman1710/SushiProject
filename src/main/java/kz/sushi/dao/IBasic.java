package kz.sushi.dao;

import kz.sushi.dao.entity.Basic;

import java.sql.SQLException;
import java.util.List;

public interface IBasic<T extends Basic> {
    void create (T model) throws SQLException;
    void update (T model) throws SQLException;
    void delete (T model) throws SQLException;
    List<T> findAll () throws SQLException;


}

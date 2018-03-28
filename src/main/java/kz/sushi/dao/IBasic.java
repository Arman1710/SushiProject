package kz.sushi.dao;

import kz.sushi.dao.entity.Basic;

import java.sql.Connection;
import java.util.List;

public interface IBasic<T extends Basic> {
    void create (T model);
    void update (T model);
    void delete (T model);
    List<T> findAll ();


}

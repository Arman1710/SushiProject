package kz.sushi.dao;

import kz.sushi.dao.entity.Locale;

import java.sql.SQLException;

public interface ILocale extends IBasic<Locale> {
    Locale getLocaleByName(String name) throws SQLException;
}

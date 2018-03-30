package kz.sushi.dao.impl;

import kz.sushi.dao.entity.Locale;
import kz.sushi.dao.ILocale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocaleDAO implements ILocale {
    final static String LOCALE_CREATE = "INSERT INTO locale (name, id) VALUES (?, ?)";
    final static String LOCALE_UPDATE = "UPDATE locale SET name=? WHERE id=?";
    final static String LOCALE_DELETE = "DELETE FROM locale";
    final static String LOCALE_FIND_ALL = "SELECT * FROM locale";
    final static String LOCALE_FIND_LOCALE_BY_NAME = "SELECT * FROM locale WHERE name=?";
    private Connection connection;

    public LocaleDAO(Connection connection) {
        this.connection = connection;
    }
    Locale locale = new Locale();
    List<Locale> localeList = new ArrayList<>();

    @Override
    public void create(Locale locale) throws SQLException {
        setToPStatement(locale, LOCALE_CREATE);
    }

    @Override
    public void update(Locale locale) throws SQLException {
        setToPStatement(locale, LOCALE_UPDATE);
    }

    @Override
    public void delete(Locale locale) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(LOCALE_DELETE)) {
            pStatement.executeUpdate();
        }
    }

    @Override
    public List<Locale> findAll() throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(LOCALE_FIND_ALL)) {
            setToLocale(resultSet);
        }
        return localeList;
    }

    @Override
    public Locale getLocaleByName(String name) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(LOCALE_FIND_LOCALE_BY_NAME)) {
            pStatement.setString(1, name);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    locale.setId(resultSet.getInt("id"));
                    localeList.add(locale);
                }
            }
        }
        return locale;
    }

    private void setToLocale(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            locale =new Locale();
            locale.setId(resultSet.getInt("id"));
            locale.setName(resultSet.getString("name"));
            localeList.add(locale);
        }
    }

    private void setToPStatement (Locale locale, String sqlCommand) throws SQLException {
        try (PreparedStatement pStatement = connection.prepareStatement(sqlCommand)) {
            pStatement.setString(1, locale.getName());
            pStatement.executeUpdate();
        }
    }
}
